/**
 *
 */
package hu.restoffice.rest.model;

import java.util.Collection;

/**
 * @author kalmankostenszky
 *
 */
public class ExpenseStub {

    public Long id;
    private PartnerStub partner;
    private String documentNo;
    private PaymentDetailsStub paymentDetails;
    private Collection<ExpenseDetailsStub> expenseDetails;

    public ExpenseStub() {

    }

    /**
     * @param id
     * @param partner
     * @param documentNo
     * @param paymentDetails
     * @param expenseDetails
     */
    public ExpenseStub(final Long id, final PartnerStub partner, final String documentNo,
            final PaymentDetailsStub paymentDetails, final Collection<ExpenseDetailsStub> expenseDetails) {
        super();
        this.id = id;
        this.partner = partner;
        this.documentNo = documentNo;
        this.paymentDetails = paymentDetails;
        this.expenseDetails = expenseDetails;
    }

    /**
     * @return the partner
     */
    public PartnerStub getPartner() {
        return partner;
    }

    /**
     * @param partner
     *            the partner to set
     */
    public void setPartner(final PartnerStub partner) {
        this.partner = partner;
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
     * @return the paymentDetails
     */
    public PaymentDetailsStub getPaymentDetails() {
        return paymentDetails;
    }

    /**
     * @param paymentDetails
     *            the paymentDetails to set
     */
    public void setPaymentDetails(final PaymentDetailsStub paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    /**
     * @return the expenseDetails
     */
    public Collection<ExpenseDetailsStub> getExpenseDetails() {
        return expenseDetails;
    }

    /**
     * @param expenseDetails
     *            the expenseDetails to set
     */
    public void setExpenseDetails(final Collection<ExpenseDetailsStub> expenseDetails) {
        this.expenseDetails = expenseDetails;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ExpenseStub [id=" + id + ", partner=" + partner + ", documentNo=" + documentNo + ", paymentDetails="
                + paymentDetails + ", expenseDetails=" + expenseDetails + "]";
    }

}
