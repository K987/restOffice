package hun.restoffice.client.converter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import hun.restoffice.client.model.DailyTransactionModel;
import hun.restoffice.client.model.EmployeeShiftModel;
import hun.restoffice.client.model.RegisterCloseModel;
import hun.restoffice.client.model.RegisterModel;
import hun.restoffice.remoteClient.domain.CalendarScheduleStub;
import hun.restoffice.remoteClient.domain.DailyTransactionStub;
import hun.restoffice.remoteClient.domain.EmployeeShiftStub;
import hun.restoffice.remoteClient.domain.RegisterCloseStub;
import hun.restoffice.remoteClient.domain.RegisterStub;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * Converts stubs to models and back
 * 
 * @author kalmankostenszky
 */
public class Converter {

	private static final Logger LOG = Logger.getLogger(Converter.class);

	/**
	 * Creates List of EmployeeShiftModels from List of CalendarScheduleStubs
	 * 
	 * @param calendarSchedules
	 * @return
	 */
	public static ObservableList<EmployeeShiftModel> toEmployeeShiftModel(List<CalendarScheduleStub> calendarSchedules) {
		ObservableList<EmployeeShiftModel> rtrn = FXCollections.observableArrayList();

		for (CalendarScheduleStub sch : calendarSchedules) {
			for (EmployeeShiftStub asg : sch.getAssignees()) {
				rtrn.add(new EmployeeShiftModel(sch.getId(), asg.getRowId(), sch.getStart(), asg.getName(), asg.getDefaultPosition(), asg.getActualPosition(),
						asg.getActualStart(), asg.getActualEnd()));
			}
		}
		return rtrn;
	}

	/**
	 * Creates RegisterCloseModel from List of RegisteStubs
	 * 
	 * @param registers
	 * @return
	 */
	public static RegisterCloseModel toRegisterCloseModel(List<RegisterCloseStub> registers) {
		List<RegisterModel> tmp = new ArrayList<>();
		for (RegisterCloseStub register : registers) {
			tmp.add(new RegisterModel(register.getRegisterStub().getRegisterId(), register.getRegisterStub().getRegisterType(), register.getCloseDate(),
					register.getCloseNo(), register.getCloseAmt().doubleValue()));
		}
		return new RegisterCloseModel(tmp);
	}

	/**
	 * Creates RegisterStub from RegisterModel
	 * 
	 * @param registerModel
	 * @return
	 */
	public static RegisterCloseStub fromRegisterModel(RegisterModel registerModel) {
		return new RegisterCloseStub(new RegisterStub(registerModel.idProperty().get(), registerModel.getType().ordinal()),
				new BigDecimal(registerModel.amountProperty().get()), registerModel.getDate().getTime(), registerModel.closeNoProperty().get());
	}

	/**
	 * Creates list of RegisterCloseStub from List of RegisterModels
	 * 
	 * @param models
	 * @return
	 */
	public static List<RegisterCloseStub> fromRegisterModel(List<RegisterModel> models) {
		List<RegisterCloseStub> rtrn = new ArrayList<>();
		for (RegisterModel model : models) {
			rtrn.add(fromRegisterModel(model));
		}
		return rtrn;
	}

	/**
	 * @param models
	 * @return
	 */
	public static List<EmployeeShiftStub> fromEmployeeShiftModel(ObservableList<EmployeeShiftModel> models) {
		List<EmployeeShiftStub> rtrn = new ArrayList<>();
		for (EmployeeShiftModel model : models) {
			rtrn.add(fromEmployyeShiftModel(model));
		}
		return rtrn;
	}

	/**
	 * @param model
	 * @return
	 */
	public static EmployeeShiftStub fromEmployyeShiftModel(EmployeeShiftModel model) {
		Instant instant = model.actualStartProperty().get().atDate(LocalDate.of(2000, 1, 1)).atZone(ZoneId.systemDefault()).toInstant();
		Date actualStart = Date.from(instant);

		instant = model.actualEndProperty().get().atDate(LocalDate.of(2000, 1, 1)).atZone(ZoneId.systemDefault()).toInstant();
		Date actualEnd = Date.from(instant);

		return new EmployeeShiftStub(model.nameProperty().get(), model.getShiftId(), model.getRowId(), actualStart, actualEnd,
				model.actualPositionProperty().get().ordinal());
	}

	/**
	 * @param models
	 * @return
	 */
	public static List<DailyTransactionStub> fromDailyTransactionModel(ObservableList<DailyTransactionModel> models) {
		List<DailyTransactionStub> rtrn = new ArrayList<>();
		for (DailyTransactionModel model : models) {
			rtrn.add(fromDailyTransactionModel(model));
		}
		return rtrn;
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	public static DailyTransactionStub fromDailyTransactionModel(DailyTransactionModel model){
		return new DailyTransactionStub(new BigDecimal(model.cashAmtProperty().get()), new BigDecimal(model.cardAmtProperty().get()),
				new BigDecimal(model.posAmtProperty().get()), model.employeeModelProperty().getRowId());
	}

}
