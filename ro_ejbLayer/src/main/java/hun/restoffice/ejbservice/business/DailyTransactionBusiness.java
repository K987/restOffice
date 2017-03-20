/**
 * 
 */
package hun.restoffice.ejbservice.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import hun.restoffice.ejbservice.converter.IncomeConverterLocal;
import hun.restoffice.ejbservice.event.EndOfDayEvent;
import hun.restoffice.persistence.entity.dailyTransaction.DailyIncome;
import hun.restoffice.persistence.entity.dailyTransaction.RegisterClose;
import hun.restoffice.persistence.entity.dailyTransaction.RegisterType;
import hun.restoffice.persistence.entity.financialTransaction.DocumentType;
import hun.restoffice.persistence.entity.financialTransaction.Income;
import hun.restoffice.persistence.entity.financialTransaction.PaymentMethod;
import hun.restoffice.persistence.exception.PersistenceServiceException;
import hun.restoffice.persistence.service.DailyTransactionServiceLocal;
import hun.restoffice.persistence.service.IncomeServiceLocal;
import hun.restoffice.persistence.service.RegisterServiceLocal;
import hun.restoffice.remoteClient.domain.IncomeStub;
import hun.restoffice.remoteClient.exception.FacadeException;

/**
 * 
 *
 * @author kalmankostenszky
 */
@Stateless(mappedName = "ejb/dailyTransactionBusiness")
public class DailyTransactionBusiness implements DailyTransactionBusinessLocal {

	private static final String CASH_TYPE = "cash";
	private static final String CARD_TYPE = "card";
	private static final String CASH_ID = "cash_id";

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.ejbservice.business.DailyTransactionBusinessLocal#closeWorkDay(java.util.Calendar)
	 */

	@EJB
	private RegisterServiceLocal rService;

	@EJB
	private DailyTransactionServiceLocal dtService;

	@EJB
	private IncomeServiceLocal iService;
	
	@EJB
	private IncomeConverterLocal iConverter;
	
	@Inject
	private Event<EndOfDayEvent> eodEvent;

	@Override
	public void closeWorkDay(Calendar workDay) throws FacadeException {
		List<RegisterClose> registers;
		List<DailyIncome> dailyIncomes;

		try {
			registers = this.rService.readRegisterClose(workDay);
			dailyIncomes = this.dtService.findByDate(workDay);
		} catch (PersistenceServiceException e) {
			throw new FacadeException(e.getLocalizedMessage());
		}

		BigDecimal cardTotal = getTotal(registers, RegisterType.CARD);
		BigDecimal cashTotal = getTotal(registers, RegisterType.CASH);

		cashTotal = cashTotal.subtract(cardTotal);

		BigDecimal cashOver = getOver(dailyIncomes, cashTotal, CASH_TYPE);
		BigDecimal cardOver = getOver(dailyIncomes, cardTotal, CARD_TYPE);

		List<Income> incomes = new ArrayList<>();
		incomes.add(new Income(makeID(registers, RegisterType.CARD), "napi bevétel", DocumentType.EXTERNAL, cashTotal, workDay, null, workDay, null, null,
				"étel-ital", PaymentMethod.CASH, "Napi forgalom"));
		incomes.add(new Income(makeID(registers, RegisterType.CASH), "napi bevétel", DocumentType.INTERNAL, cardTotal, workDay, null, workDay, null, null,
				"étel-ital", PaymentMethod.DEBT_CARD, "Napi forgalom"));
		incomes.add(new Income("cardOver-" + workDay.getTime().toString(), "napi bevétel", DocumentType.INTERNAL, cardOver, workDay, null, workDay, null, null,
				"étel-ital", PaymentMethod.DEBT_CARD, "Napi forgalom"));
		incomes.add(new Income("cashOver-" + workDay.getTime().toString(), "napi bevétel", DocumentType.INTERNAL, cashOver, workDay, null, workDay, null, null,
				"étel-ital", PaymentMethod.CASH, "Napi forgalom"));

		try {
			this.iService.insertAll(incomes);
		} catch (PersistenceServiceException e) {
			throw new FacadeException(e.getLocalizedMessage());
		}
		
		List<IncomeStub> inc = this.iConverter.to(incomes);
		eodEvent.fire(new EndOfDayEvent(inc));

	}

	/**
	 * @param cashId
	 * @return
	 */
	private String makeID(List<RegisterClose> registers, RegisterType type) {
		StringBuilder sb = new StringBuilder();
		for (RegisterClose register : registers) {
			if (register.getRegister().getRegisterType() == type)
				sb.append(register.getRegister().getRegisterId() + ":"+register.getId().getRegisterCloseNo()+"-");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("-" + type);
		return sb.toString();
	}

	/**
	 * @param employeeSchedules
	 * @param cashTotal
	 * @return
	 */
	private BigDecimal getOver(List<DailyIncome> dailyIncomes, BigDecimal total, String type) {
		BigDecimal rtrn = new BigDecimal("0");
		for (DailyIncome dailyIncome : dailyIncomes) {
			if (type.equals(CASH_TYPE))
				rtrn = rtrn.add(dailyIncome.getCashSum());
			else if (type.equals(CARD_TYPE))
				rtrn = rtrn.add(dailyIncome.getCardSum());
		}
		rtrn = rtrn.subtract(total);
		return rtrn;
	}

	/**
	 * @param registers
	 * @param type
	 * @return
	 */
	private BigDecimal getTotal(List<RegisterClose> registers, RegisterType type) {
		BigDecimal rtrn = new BigDecimal("0");
		for (RegisterClose registerClose : registers) {
			if (registerClose.getRegister().getRegisterType() == type)
				rtrn = rtrn.add(registerClose.getRegisterCloseAmt());
		}
		return rtrn;
	}

}
