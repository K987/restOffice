/**
 *
 */
package hu.restoffice.rest.model;

import java.time.LocalDate;

/**
 * @author kalmankostenszky
 *
 */
public class PaymentDetailsStub {

    private DocumentType documentType;
    private PaymentMethod paymentMethod;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate expiryDate;
    private LocalDate paidDate;

    public PaymentDetailsStub() {

    }

    /**
     * @param documentType
     * @param paymentMethod
     * @param issueDate
     * @param dueDate
     * @param expiryDate
     * @param paidDate
     */
    public PaymentDetailsStub(final DocumentType documentType, final PaymentMethod paymentMethod,
            final LocalDate issueDate, final LocalDate dueDate, final LocalDate expiryDate, final LocalDate paidDate) {
        super();
        this.documentType = documentType;
        this.paymentMethod = paymentMethod;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.expiryDate = expiryDate;
        this.paidDate = paidDate;
    }

    /**
     * @return the documentType
     */
    public DocumentType getDocumentType() {
        return documentType;
    }

    /**
     * @param documentType
     *            the documentType to set
     */
    public void setDocumentType(final DocumentType documentType) {
        this.documentType = documentType;
    }

    /**
     * @return the paymentMethod
     */
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * @param paymentMethod
     *            the paymentMethod to set
     */
    public void setPaymentMethod(final PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * @return the issueDate
     */
    public LocalDate getIssueDate() {
        return issueDate;
    }

    /**
     * @param issueDate
     *            the issueDate to set
     */
    public void setIssueDate(final LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * @return the dueDate
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate
     *            the dueDate to set
     */
    public void setDueDate(final LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @return the expiryDate
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate
     *            the expiryDate to set
     */
    public void setExpiryDate(final LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * @return the paidDate
     */
    public LocalDate getPaidDate() {
        return paidDate;
    }

    /**
     * @param paidDate
     *            the paidDate to set
     */
    public void setPaidDate(final LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PaymentDetailsStub [documentType=" + documentType + ", paymentMethod=" + paymentMethod + ", issueDate="
                + issueDate + ", dueDate=" + dueDate + ", expiryDate=" + expiryDate + ", paidDate=" + paidDate + "]";
    }

}
