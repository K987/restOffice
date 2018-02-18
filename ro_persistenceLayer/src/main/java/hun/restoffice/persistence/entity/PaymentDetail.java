package hun.restoffice.persistence.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import hun.restoffice.persistence.type.DocumentTypes;
import hun.restoffice.persistence.type.PaymentMethodType;
import hun.restoffice.persistence.type.PaymentTypes;


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
    private LocalDate fulfillmentDt;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "document_type_cd", nullable = false)
    private DocumentTypes documentType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "payment_type_cd", nullable = false)
    private PaymentTypes paymentType;

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
        return fulfillmentDt;
    }

    public void setPaidDate(final LocalDate paidDt) {
        fulfillmentDt = paidDt;
    }

    public DocumentTypes getDocumentType() {
        return documentType;
    }

    public void setDocumentType(final DocumentTypes documentType) {
        this.documentType = documentType;
    }

    public PaymentMethodType getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(final PaymentMethodType paymentMethod) {
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
                + issueDate + ", paidDate=" + fulfillmentDt + ", documentType=" + documentType + ", paymentMethod="
                + paymentMethod + "]";
    }
}