package hun.restoffice.persistence.entity.financialTransaction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the incomes database table.
 * 
 * @author kalmankostenszky
 */
@Entity
@Table(name = "incomes")

@NamedQueries(value = { 
		@NamedQuery(name = Income.FIND_ALL, query = "SELECT i FROM Income i JOIN FETCH i.party"),
		@NamedQuery(name = Income.COUNT_BY_ID, query = "SELECT COUNT(i) FROM Income i WHERE LOWER(i.docId)=:"+Income.ID)
})

@AttributeOverrides(value = { @AttributeOverride(name = "docId", column = @Column(name = "income_doc_id")),
		@AttributeOverride(name = "docType", column = @Column(name = "income_doc_type")),
		@AttributeOverride(name = "payMethod", column = @Column(name = "income_payment_method")),
		@AttributeOverride(name = "grossTotal", column = @Column(name = "income_gross_amount")),
		@AttributeOverride(name = "description", column = @Column(name = "income_description")),
		@AttributeOverride(name = "registered", column = @Column(name = "income_issue_date")),
		@AttributeOverride(name = "expiry", column = @Column(name = "income_expiry_date")),
		@AttributeOverride(name = "payed", column = @Column(name = "income_payed_date")),
		@AttributeOverride(name = "accPeriod.startDate", column = @Column(name = "income_acc_per_start")),
		@AttributeOverride(name = "accPeriod.endDate", column = @Column(name = "income_acc_per_end")),
		@AttributeOverride(name = "lastModifiedAt", column = @Column(name = "income_last_modified_dt")) })
@AssociationOverrides(value = { @AssociationOverride(name = "party", joinColumns = @JoinColumn(name = "income_liable", referencedColumnName = "partner_id")),
		@AssociationOverride(name = "lastModifiedBy", joinColumns = @JoinColumn(name = "income_last_modified_by", referencedColumnName = "user_id")) })
public class Income extends FinancialTransaction implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String COUNT_BY_ID = "Income.countById";
	public static final String FIND_ALL = "Income.findAll";
	public static final String ID = "id";

	// fields

	// bi-directional many-to-one association to IncType
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "income_type", nullable = false)
	private IncType incType;

	@Transient
	private String incTypeName;

	@Transient
	private String partnerName;

	// constructors
	public Income() {
	}



	/**
	 * @param docId
	 * @param description
	 * @param documentType
	 * @param grossTotal
	 * @param registered
	 * @param expiry
	 * @param payed
	 * @param accPeriodStart
	 * @param accPeriodEnd
	 * @param incomeType
	 * @param paymentMethod
	 * @param partner
	 */
	public Income(String docId, String description, DocumentType documentType, BigDecimal grossTotal, Calendar registered, Calendar expiry, Calendar payed,
			Calendar accPeriodStart, Calendar accPeriodEnd, String incomeType, PaymentMethod paymentMethod, String partner) {
		super.setDocId(docId);
		super.setDescription(description);
		super.setDocType(documentType);
		super.setPayMethod(paymentMethod);
		super.setGrossTotal(grossTotal);
		super.setRegistered(convertDate(registered));
		super.setExpiry(convertDate(expiry));
		super.setPayed(convertDate(payed));
		super.setAccPeriod(new AccountingPeriod(convertDate(accPeriodStart), convertDate(accPeriodEnd)));
		this.incTypeName = incomeType;
		this.partnerName = partner;
	}



	/**
	 * @param registered
	 * @return
	 */
	private Date convertDate(Calendar registered) {
		if (registered == null)
			return null;
		return registered.getTime();
	}

	// getters setters
	public IncType getIncType() {
		return this.incType;
	}

	public void setIncType(IncType incType) {
		this.incType = incType;
	}

	/**
	 * @return the incTypeName
	 */
	public String getIncTypeName() {
		return incTypeName;
	}

	/**
	 * @return the partnerName
	 */
	public String getPartnerName() {
		return partnerName;
	}
	
	
}