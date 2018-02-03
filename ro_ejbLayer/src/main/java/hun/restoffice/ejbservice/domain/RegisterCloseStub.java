/**
 * 
 */
package hun.restoffice.ejbservice.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 *  DTO of RegisterClose
 *
 * @author kalmankostenszky
 */
public class RegisterCloseStub implements Serializable {

	private static final long serialVersionUID = 1L;


	private final RegisterStub registerStub;
	private final BigDecimal closeAmt;
	private final Calendar closeDate;
	private final int closeNo;
	

	/**
	 * @param regStub
	 * @param registerCloseAmt
	 * @param registerCloseDate
	 * @param registerCloseNo 
	 */
	public RegisterCloseStub(RegisterStub regStub, BigDecimal registerCloseAmt, Date registerCloseDate, Integer registerCloseNo) {
		this.registerStub = regStub;
		this.closeAmt = registerCloseAmt;
		if(registerCloseDate != null)
			(this.closeDate = Calendar.getInstance()).setTime(registerCloseDate);
		else
			this.closeDate = null;
		this.closeNo = registerCloseNo;
	}

	/**
	 * @return the registerStub
	 */
	public RegisterStub getRegisterStub() {
		return registerStub;
	}


	/**
	 * @return the closeAmt
	 */
	public BigDecimal getCloseAmt() {
		return closeAmt;
	}


	/**
	 * @return the closeDate
	 */
	public Calendar getCloseDate() {
		return closeDate;
	}


	/**
	 * @return the closeNo
	 */
	public int getCloseNo() {
		return closeNo;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("RegisterCloseStub [registerStub=%s, closeAmt=%s, closeDate=%s, closeNo=%s]", registerStub, closeAmt, closeDate, closeNo);
	}
	
}
