/**
 * 
 */
package hun.restoffice.ejbservice.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * 
 *
 * @author kalmankostenszky
 */
public class IncomeStub implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String docId;
	private final String partner;
	private final PaymentMethodStub payMethod;
	private final String incomeType;
	private final Calendar registered;
	private final Calendar payed;
	private final BigDecimal grossTotal;
	private final Calendar expiry;
	private final DocTypeStub docType;
	private final String description;
	private final Calendar accPeriodEnd;
	private final Calendar accPeriodStart;


	/**
	 * @param docId
	 * @param docType
	 * @param partner
	 * @param description
	 * @param grossTotal
	 * @param payMethod
	 * @param incomeType
	 * @param registered
	 * @param payed
	 * @param expiry
	 * @param accPeriodStart
	 * @param accPeriodEnd
	 */
	public IncomeStub(String docId, DocTypeStub docType, String partner, String description, BigDecimal grossTotal, PaymentMethodStub payMethod, String incomeType, Calendar registered, 
			  Calendar payed, Calendar expiry, Calendar accPeriodStart, Calendar accPeriodEnd ) {
		super();
		this.partner = partner;
		this.payMethod = payMethod;
		this.incomeType = incomeType;
		this.accPeriodStart = accPeriodStart;
		this.accPeriodEnd = accPeriodEnd;
		this.registered = registered;
		this.payed = payed;
		this.grossTotal = grossTotal;
		this.expiry = expiry;
		this.docType = docType;
		this.docId = docId;
		this.description = description;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	/**
	 * @return
	 */
	public String getDocId() {
		// TODO Auto-generated method stub
		return docId;
	}

	/**
	 * @return
	 */
	public DocTypeStub getDocType() {
		// TODO Auto-generated method stub
		return docType;
	}

	/**
	 * @return
	 */
	public Calendar getExpiry() {
		// TODO Auto-generated method stub
		return expiry;
	}

	/**
	 * @return
	 */
	public BigDecimal getGrossTotal() {
		// TODO Auto-generated method stub
		return grossTotal;
	}

	/**
	 * @return
	 */
	public Calendar getPayed() {
		// TODO Auto-generated method stub
		return payed;
	}

	/**
	 * @return
	 */
	public Calendar getRegistered() {
		// TODO Auto-generated method stub
		return registered;
	}

	/**
	 * @return
	 */
	public Calendar getAccPeriodStart() {
		// TODO Auto-generated method stub
		return accPeriodStart;
	}

	/**
	 * @return
	 */
	public String getIncomeType() {
		// TODO Auto-generated method stub
		return incomeType;
	}

	/**
	 * @return
	 */
	public PaymentMethodStub getPayMethod() {
		// TODO Auto-generated method stub
		return payMethod;
	}

	/**
	 * @return
	 */
	public String getPartner() {
		// TODO Auto-generated method stub
		return partner;
	}

	/**
	 * @return the accPeriodEnd
	 */
	public Calendar getAccPeriodEnd() {
		return accPeriodEnd;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IncomeStub [" + (docId != null ? "docId=" + docId + ", " : "") + (partner != null ? "partner=" + partner + ", " : "")
				+ (payMethod != null ? "payMethod=" + payMethod + ", " : "") + (incomeType != null ? "incomeType=" + incomeType + ", " : "")
				+ (accPeriodStart != null ? "accPeriodStart=" + accPeriodStart.getTimeInMillis() + ", " : "") + (registered != null ? "registered=" + registered.getTimeInMillis() + ", " : "")
				+ (payed != null ? "payed=" + payed.getTimeInMillis() + ", " : "") + (grossTotal != null ? "grossTotal=" + grossTotal + ", " : "")
				+ (expiry != null ? "expiry=" + expiry + ", " : "") + (docType != null ? "docType=" + docType + ", " : "")
				+ (description != null ? "description=" + description + ", " : "") + (accPeriodEnd != null ? "accPeriodEnd=" + accPeriodEnd.getTimeInMillis() : "") + "]";
	}



}
