package hun.restoffice.persistence.entity.financialTransaction;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The embeded class for financial periods in FinancialTransaction class
 *
 * @author kalmankostenszky
 *
 */
@Embeddable
public class AccountingPeriod {

	// fields
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Transient
	private Integer periodLength;

	// constructors
	public AccountingPeriod() {

	}


	/**
	 * @param startDate
	 * @param endDate
	 */
	public AccountingPeriod(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	// public methods

	// private methods
	/**
	 * sets variable period length by counting the months between start and and dates
	 */
	private void setMonths() {
		this.periodLength = 1;
		if (this.startDate != null && this.endDate != null && this.endDate.getTime() >= this.startDate.getTime()) {
			Calendar start = Calendar.getInstance();
			Calendar end = Calendar.getInstance();
			start.setTime(startDate);
			end.setTime(endDate);
			int years = end.get(Calendar.YEAR) - start.get(Calendar.YEAR);
			int months = years * 12 + end.get(Calendar.MONTH) - end.get(Calendar.MONTH);
			this.periodLength += months;
		}
	}

	// getters setters
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		setMonths();
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
		setMonths();
	}

	/**
	 * @return the monthsBetween
	 */
	public int getPeriodLength() {
		if (periodLength == null) {
			setMonths();
		}
		return periodLength;
	}

}
