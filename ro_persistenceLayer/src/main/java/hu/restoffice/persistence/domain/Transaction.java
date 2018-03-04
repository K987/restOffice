package hu.restoffice.persistence.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 *
 */
@MappedSuperclass
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTION_ID_GENERATOR")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "document_no", unique = true, nullable = false, length = 50)
    private String documentNo;

    // uni-directional many-to-one association to Partner
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id", nullable = false)
    private Partner partner;

    @Column(name = "due_dt", nullable = false)
    private LocalDate due;

    @Column(name = "expiry_dt")
    private LocalDate expiry;

    @Column(name = "issue_dt", nullable = false)
    private LocalDate issue;

    @Column(name = "paid_dt")
    private LocalDate paid;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "document_type_cd", nullable = false)
    private DocumentTypes documentType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "payment_type_cd", nullable = false)
    private PaymentTypes paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deposit_box_id", nullable = false)
    private DepositBox depositBox;

    @Embedded
    private UserAction action;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the documentNo
     */
    public String getDocumentNo() {
        return documentNo;
    }

    /**
     * @param documentNo
     *            the documentNo to set
     */
    public void setDocumentNo(final String documentNo) {
        this.documentNo = documentNo;
    }

    /**
     * @return the partner
     */
    public Partner getPartner() {
        return partner;
    }

    /**
     * @param partner
     *            the partner to set
     */
    public void setPartner(final Partner partner) {
        this.partner = partner;
    }

    /**
     * @return the due
     */
    public LocalDate getDue() {
        return due;
    }

    /**
     * @param due
     *            the due to set
     */
    public void setDue(final LocalDate due) {
        this.due = due;
    }

    /**
     * @return the expiry
     */
    public LocalDate getExpiry() {
        return expiry;
    }

    /**
     * @param expiry
     *            the expiry to set
     */
    public void setExpiry(final LocalDate expiry) {
        this.expiry = expiry;
    }

    /**
     * @return the issue
     */
    public LocalDate getIssue() {
        return issue;
    }

    /**
     * @param issue
     *            the issue to set
     */
    public void setIssue(final LocalDate issue) {
        this.issue = issue;
    }

    /**
     * @return the paid
     */
    public LocalDate getPaid() {
        return paid;
    }

    /**
     * @param paid
     *            the paid to set
     */
    public void setPaid(final LocalDate paid) {
        this.paid = paid;
    }

    /**
     * @return the documentType
     */
    public DocumentTypes getDocumentType() {
        return documentType;
    }

    /**
     * @param documentType
     *            the documentType to set
     */
    public void setDocumentType(final DocumentTypes documentType) {
        this.documentType = documentType;
    }

    /**
     * @return the paymentType
     */
    public PaymentTypes getPaymentType() {
        return paymentType;
    }

    /**
     * @param paymentType
     *            the paymentType to set
     */
    public void setPaymentType(final PaymentTypes paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * @return the action
     */
    public UserAction getAction() {
        return action;
    }

    /**
     * @param action
     *            the action to set
     */
    protected void setAction(final UserAction action) {
        this.action = action;
    }

    /**
     * @return the depositBox
     */
    public DepositBox getDepositBox() {
        return depositBox;
    }

    /**
     * @param depositBox
     *            the depositBox to set
     */
    public void setDepositBox(final DepositBox depositBox) {
        this.depositBox = depositBox;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Transaction [id=" + id + ", documentNo=" + documentNo + ", partner=" + partner + ", due=" + due
                + ", expiry=" + expiry + ", issue=" + issue + ", paid=" + paid + ", documentType=" + documentType
                + ", paymentType=" + paymentType + ", depositBox=" + depositBox + ", action=" + action + "]";
    }

}
