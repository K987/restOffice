package hun.restoffice.persistence.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import hun.restoffice.persistence.type.DocumentType;
import hun.restoffice.persistence.type.PaymentMethod;


/**
 * The persistent class for the payment_detail database table.
 *
 */
@Entity
@Table(name="payment_detail")
@NamedQuery(name="PaymentDetail.findAll", query="SELECT p FROM PaymentDetail p")
public class PaymentDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="PAYMENT_DETAIL_PAYMENTDETAILID_GENERATOR", sequenceName="PAYMENT_DETAIL_PAYMENT_DETAIL_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAYMENT_DETAIL_PAYMENTDETAILID_GENERATOR")
    @Column(name = "payment_detail_id", updatable = false)
    private Long id;

    @Column(name="due_dt", nullable=false)
    private LocalDate dueDate;

    @Column(name="expiry_dt")
    private LocalDate expiryDate;

    @Column(name="issue_dt", nullable=false)
    private LocalDate issueDate;

    @Column(name="paid_dt")
    private LocalDate paidDate;

    //uni-directional many-to-one association to DocumentType
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="document_type_id", nullable=false)
    private DocumentType documentType;

    //uni-directional many-to-one association to PaymentMethod
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="payment_method_id", nullable=false)
    private PaymentMethod paymentMethod;

    public PaymentDetail() {
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(final LocalDate dueDt) {
        dueDate = dueDt;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(final LocalDate expiryDt) {
        expiryDate = expiryDt;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(final LocalDate issueDt) {
        issueDate = issueDt;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(final LocalDate paidDt) {
        paidDate = paidDt;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(final DocumentType documentType) {
        this.documentType = documentType;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(final PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PaymentDetail [id=" + id + ", dueDate=" + dueDate + ", expiryDate=" + expiryDate + ", issueDate="
                + issueDate + ", paidDate=" + paidDate + ", documentType=" + documentType + ", paymentMethod="
                + paymentMethod + "]";
    }
}