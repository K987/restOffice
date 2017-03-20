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

import hun.restoffice.persistence.entity.partner.Partner;

/**
 * The persistent class for the expenses database table.
 * 
 */
/**
 * @author kalmankostenszky
 *
 */
@Entity
@Table(name = "expenses")
@NamedQueries(value = { @NamedQuery(name = Expense.FIND_ALL, query = "SELECT e FROM Expense e JOIN FETCH e.party JOIN FETCH e.costCenter JOIN FETCH e.expType "),
		@NamedQuery(name = Expense.READ_BY_DOC_ID, query = "SELECT e FROM Expense e WHERE LOWER(docId)=:" + Expense.DOC_ID),
		@NamedQuery(name = Expense.READ_FILTERED, query = "SELECT e FROM Expense e JOIN FETCH e.party WHERE " 
				+ "1=1" 
				+ " AND ( :"+ Expense.PARTNER_ID + " =-1"
						+ " OR e.party.id=:" + Expense.PARTNER_ID 
				+ ")" 
				+ " AND ( :"+Expense.COSTCENTER_ID+ " =-1"
						+ " OR e.costCenter.id=:" + Expense.COSTCENTER_ID
				+ ")"
				+ "AND ( :" + Expense.COSTTYPE_ID+ " =-1"
						+ " OR e.expType.id=:" + Expense.COSTTYPE_ID 
				+ ") "
				+ "AND ( :" + Expense.PAYMENT_METHOD + " =-1"
					+ " OR e.payMethod =:"+ Expense.PAYMENT_METHOD 
				+ " )"
				+ "AND ( 0 =:"+Expense.IS_PAYED+" "
						+ "OR (-1 =:"+Expense.IS_PAYED+" AND e.payed is null) "
						+ "OR (1 =:"+Expense.IS_PAYED+" AND e.payed is not null)"
						+ ") "
				),
		@NamedQuery(name= Expense.COUNT, query="SELECT COUNT(e) FROM Expense e WHERE LOWER(docId)=:"+Expense.DOC_ID)
		})

@AttributeOverrides(value = { @AttributeOverride(name = "docId", column = @Column(name = "expense_doc_id")),
		@AttributeOverride(name = "docType", column = @Column(name = "expense_doc_type")),
		@AttributeOverride(name = "payMethod", column = @Column(name = "expense_payment_method")),
		@AttributeOverride(name = "grossTotal", column = @Column(name = "expense_gross_amount")),
		@AttributeOverride(name = "description", column = @Column(name = "expense_description")),
		@AttributeOverride(name = "registered", column = @Column(name = "expense_recieved_date")),
		@AttributeOverride(name = "expiry", column = @Column(name = "expense_expiry_date")),
		@AttributeOverride(name = "payed", column = @Column(name = "expense_payed_date")),
		@AttributeOverride(name = "accPeriod.startDate", column = @Column(name = "expense_acc_per_start")),
		@AttributeOverride(name = "accPeriod.endDate", column = @Column(name = "expense_acc_per_end")),
		@AttributeOverride(name = "lastModifiedAt", column = @Column(name = "expense_last_modified_dt")) })
@AssociationOverrides(value = { @AssociationOverride(name = "party", joinColumns = @JoinColumn(name = "expense_issuer", referencedColumnName = "partner_id")),
		@AssociationOverride(name = "lastModifiedBy", joinColumns = @JoinColumn(name = "expense_last_modified_by", referencedColumnName = "user_id")) })
public class Expense extends FinancialTransaction implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Expense.findAll";
	public static final String READ_BY_DOC_ID = "Expense.readById";
	public static final String READ_FILTERED = "Expense.readFiltered";
	public static final String COUNT = "Expense.count";

	public static final String DOC_ID = "docId";
	public static final String PARTNER_ID = "partnerId";
	public static final String COSTCENTER_ID = "costCenterId";
	public static final String COSTTYPE_ID = "costTypeId";
	public static final String PAYMENT_METHOD = "paymentMethod";
	public static final String IS_PAYED = "isPayed";


	// fields

	// bi-directional many-to-one association to CostCenter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "expense_costcenter", nullable = false, referencedColumnName = "cost_center_id")
	private CostCenter costCenter;

	// bi-directional many-to-one association to ExpType
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "expense_type", nullable = false, referencedColumnName = "exp_type_id")
	private ExpType expType;


	@Transient
	private String costCenterName;

	@Transient
	private String costTypeName;

	// constructors
	public Expense() {
	}

	/**
	 * @param docId
	 * @param ordinal
	 * @param id
	 * @param ordinal2
	 * @param grossTotal
	 * @param description
	 * @param registered
	 * @param expiry
	 * @param payed
	 * @param accPeriodStart
	 * @param accPeriodEnd
	 * @param costCenter2
	 * @param costType
	 */
	public Expense(String docId, int ordinal, Partner partner, int ordinal2, BigDecimal grossTotal, String description, Calendar registered, Calendar expiry,
			Calendar payed, Calendar accPeriodStart, Calendar accPeriodEnd, String costCenter2, String costType) {
		setDocId(docId.trim());
		setDocType(DocumentType.values()[ordinal]);
		setParty(partner);
		setPayMethod(PaymentMethod.values()[ordinal2]);
		setGrossTotal(grossTotal);
		setDescription(description);
		setRegistered(convertDate(registered));
		setExpiry(convertDate(expiry));
		setPayed(convertDate(payed));
		setAccPeriod(new AccountingPeriod(convertDate(accPeriodStart), convertDate(accPeriodEnd)));
		this.costCenterName = costCenter2;
		this.costTypeName = costType;
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
	/**
	 * @return
	 */
	public CostCenter getCostCenter() {
		return this.costCenter;
	}

	public void setCostCenter(CostCenter costCenter) {
		this.costCenter = costCenter;
	}

	public ExpType getExpType() {
		return this.expType;
	}

	public void setExpType(ExpType expType) {
		this.expType = expType;
	}

	
	/**
	 * @return the costCenterName
	 */
	public String getCostCenterName() {
		return costCenterName;
	}

	/**
	 * @return the costTypeName
	 */
	public String getCostTypeName() {
		return costTypeName;
	}

	/**
	 * turns gt 0 to lt 0
	 */
	@Override
	public void setGrossTotal(BigDecimal grossTotal) {
		if (grossTotal.compareTo(new BigDecimal(0)) == 1)
			grossTotal.negate();
		super.setGrossTotal(grossTotal);
	}

	/**
	 * @param expense
	 */
	public void update(Expense expense) {
		
		setDocType(expense.getDocType());
		setParty(expense.getParty());
		setPayMethod(expense.getPayMethod());
		setGrossTotal(expense.getGrossTotal());
		setDescription(expense.getDescription());
		setRegistered(expense.getRegistered());
		setExpiry(expense.getExpiry());
		setPayed(expense.getPayed());
		setAccPeriod(expense.getAccPeriod());
		setCostCenter(expense.getCostCenter());
		setExpType(expense.getExpType());
		setLastModifiedAt(Calendar.getInstance().getTime());
	}
}