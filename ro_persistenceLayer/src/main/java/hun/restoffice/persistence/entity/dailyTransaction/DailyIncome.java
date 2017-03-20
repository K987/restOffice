package hun.restoffice.persistence.entity.dailyTransaction;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import hun.restoffice.persistence.entity.employee.EmployeeShift;

/**
 * The persistent class for the daily_incomes database table.
 * 
 * @author kalmankostenszky
 */
@Entity
@Table(name = "daily_incomes")
// TODO: add static final fields for qry name and params
@NamedQueries({ @NamedQuery(name = DailyIncome.FIND_ALL, query = "SELECT d FROM DailyIncome d JOIN FETCH d.employeeShift es"),
		@NamedQuery(name = DailyIncome.FIND_BY_DATE, query = "SELECT DISTINCT d FROM DailyIncome d JOIN d.employeeShift es JOIN es.shift s WHERE s.startDate =:"
				+ DailyIncome.DATE) })
public class DailyIncome implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "DailyIncome.findAll";
	public static final String FIND_BY_DATE = "DailyIncome.findByDate";

	public static final String DATE = "date";

	@Id
	@SequenceGenerator(name = "DAILY_INCOMES_DAILYINCOMEID_GENERATOR", sequenceName = "DAILY_INCOMES_DAILY_INCOME_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DAILY_INCOMES_DAILYINCOMEID_GENERATOR")
	@Column(name = "daily_income_id")
	private Integer id;

	@Column(name = "daily_income_card")
	private BigDecimal cardSum;

	@Column(name = "daily_income_cash")
	private BigDecimal cashSum;

	// TODO: add not nullable in DB
	@OneToOne()
	@JoinColumn(name = "daily_income_employee_shift", referencedColumnName = "employee_shift_id", nullable = false, unique = true)
	private EmployeeShift employeeShift;

	@Column(name = "daily_pos_sum", nullable = false)
	private BigDecimal posSum;

	@Transient
	private int rowId;

	public DailyIncome() {
	}

	/**
	 * @param pos
	 * @param cash
	 * @param card
	 * @param rowId
	 */
	public DailyIncome(BigDecimal pos, BigDecimal cash, BigDecimal card, int rowId) {
		this.posSum = pos;
		this.cashSum = cash;
		this.cardSum = card;
		this.rowId = rowId;
	}

	// public Integer getId() {
	// return this.id;
	// }

	/**
	 * @param posSum2
	 * @param cashSum2
	 * @param cardSum2
	 * @param employeeShift
	 */
	public DailyIncome(BigDecimal posSum2, BigDecimal cashSum2, BigDecimal cardSum2, EmployeeShift employeeShift) {
		this(posSum2, cashSum2, cardSum2, 0);
		this.employeeShift = employeeShift;

	}

	public BigDecimal getCardSum() {
		return this.cardSum;
	}

	public void setCardSum(BigDecimal dailyIncomeCard) {
		this.cardSum = dailyIncomeCard;
	}

	public BigDecimal getCashSum() {
		return this.cashSum;
	}

	public void setCashSum(BigDecimal dailyIncomeCash) {
		this.cashSum = dailyIncomeCash;
	}

	public EmployeeShift getEmployeeShift() {
		return this.employeeShift;
	}

	// TODO: set map connection vica-versa
	public void setEmployeeShift(EmployeeShift dailyIncomeEmployeeShift) {
		this.employeeShift = dailyIncomeEmployeeShift;
	}

	public BigDecimal getPosSum() {
		return this.posSum;
	}

	public void setPosSum(BigDecimal dailyPosSum) {
		this.posSum = dailyPosSum;
	}

	/**
	 * @return the rowId
	 */
	public int getRowId() {
		return rowId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("DailyIncome [ cardSum=%s, cashSum=%s, dailyIncomeEmployeeShift=%s, posSum=%s]", cardSum, cashSum, employeeShift,
				posSum);
	}

}