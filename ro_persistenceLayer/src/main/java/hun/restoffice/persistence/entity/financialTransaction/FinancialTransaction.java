/**
 * 
 */
package hun.restoffice.persistence.entity.financialTransaction;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hun.restoffice.persistence.entity.User;
import hun.restoffice.persistence.entity.partner.Partner;

/**
 * Superclass for expense and income persistence entities
 *
 * @author kalmankostenszky
 *
 */
@MappedSuperclass
public class FinancialTransaction {

	// fields

	@Id
	@Column(length = 100)
	private String docId;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private DocumentType docType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Partner party;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private PaymentMethod payMethod;

	@Column(nullable = false)
	private BigDecimal grossTotal;

	@Column(length = 200)
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date registered;

	@Temporal(TemporalType.DATE)
	private Date expiry;

	@Temporal(TemporalType.DATE)
	private Date payed;

	@Embedded
	private AccountingPeriod accPeriod;

	private Date lastModifiedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	private User lastModifiedBy;

	// constructors
	public FinancialTransaction() {

	}

	// getters and setters
	/**
	 * @return the docId
	 */
	public String getDocId() {
		return docId;
	}

	/**
	 * @param docId
	 *            the docId to set
	 */
	public void setDocId(String docId) {
		this.docId = docId;
	}

	/**
	 * @return the docType
	 */
	public DocumentType getDocType() {
		return docType;
	}

	/**
	 * @param docType
	 *            the docType to set
	 */
	public void setDocType(DocumentType docType) {
		this.docType = docType;
	}

	/**
	 * @return the party
	 */
	public Partner getParty() {
		return party;
	}

	/**
	 * @param party
	 *            the party to set
	 */
	public void setParty(Partner party) {
		this.party = party;
	}

	/**
	 * @return the payMethod
	 */
	public PaymentMethod getPayMethod() {
		return payMethod;
	}

	/**
	 * @param payMethod
	 *            the payMethod to set
	 */
	public void setPayMethod(PaymentMethod payMethod) {
		this.payMethod = payMethod;
	}

	/**
	 * @return the grossTotal
	 */
	public BigDecimal getGrossTotal() {
		return grossTotal;
	}

	/**
	 * @param grossTotal
	 *            the grossTotal to set
	 */
	public void setGrossTotal(BigDecimal grossTotal) {
		this.grossTotal = grossTotal;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the registered
	 */
	public Date getRegistered() {
		return registered;
	}

	/**
	 * @param registered
	 *            the registered to set
	 */
	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	/**
	 * @return the expiry
	 */
	public Date getExpiry() {
		return expiry;
	}

	/**
	 * @param expiry
	 *            the expiry to set
	 */
	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	/**
	 * @return the payed
	 */
	public Date getPayed() {
		return payed;
	}

	/**
	 * @param payed
	 *            the payed to set
	 */
	public void setPayed(Date payed) {
		this.payed = payed;
	}

	/**
	 * @return the accPeriod
	 */
	public AccountingPeriod getAccPeriod() {
		return accPeriod;
	}

	/**
	 * @param accPeriod
	 *            the accPeriod to set
	 */
	public void setAccPeriod(AccountingPeriod accPeriod) {
		this.accPeriod = accPeriod;
	}

	/**
	 * @return the lastModifiedAt
	 */
	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}

	/**
	 * @param lastModifiedAt
	 *            the lastModifiedAt to set
	 */
	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	/**
	 * @return the lastModifiedBy
	 */
	public User getLastModifiedBy() {
		return lastModifiedBy;
	}

	/**
	 * @param lastModifiedBy
	 *            the lastModifiedBy to set
	 */
	public void setLastModifiedBy(User lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

}
