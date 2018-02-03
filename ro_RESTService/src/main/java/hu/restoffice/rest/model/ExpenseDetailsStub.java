/**
 *
 */
package hu.restoffice.rest.model;

import hun.restoffice.ejbservice.domain.CostCenterStub;
import hun.restoffice.ejbservice.domain.ExpenseTypeStub;

/**
 * @author kalmankostenszky
 *
 */
public class ExpenseDetailsStub {

    private Long id;
    private Double netTotal;
    private Double grossTotal;
    private Double vatRate;
    private CostCenterStub costCenter;
    private ExpenseTypeStub expseneType;

    public ExpenseDetailsStub() {

    }

    /**
     * @param id
     * @param netTotal
     * @param grossTotal
     * @param vatRate
     * @param costCenter
     * @param expseneType
     */
    public ExpenseDetailsStub(final Long id, final double netTotal, final double grossTotal, final double vatRate,
            final CostCenterStub costCenter, final ExpenseTypeStub expseneType) {
        super();
        this.id = id;
        this.netTotal = netTotal;
        this.grossTotal = grossTotal;
        this.vatRate = vatRate;
        this.costCenter = costCenter;
        this.expseneType = expseneType;
    }

    /**
     * @return the netTotal
     */
    public Double getNetTotal() {
        return netTotal;
    }

    /**
     * @param netTotal
     *            the netTotal to set
     */
    public void setNetTotal(final Double netTotal) {
        this.netTotal = netTotal;
        grossTotal = this.netTotal * (1 + vatRate);
    }

    /**
     * @return the grossTotal
     */
    public Double getGrossTotal() {
        return grossTotal;
    }

    /**
     * @param grossTotal
     *            the grossTotal to set
     */
    public void setGrossTotal(final Double grossTotal) {
        this.grossTotal = grossTotal;
        netTotal = this.grossTotal / (1 + vatRate);
    }

    /**
     * @return the vatRate
     */
    public Double getVatRate() {
        return vatRate;
    }

    /**
     * @param vatRate
     *            the vatRate to set
     */
    public void setVatRate(final Double vatRate) {
        this.vatRate = vatRate;
        if (netTotal != null) {
            grossTotal = netTotal * (1 + this.vatRate);
        } else if (grossTotal != null) {
            netTotal = grossTotal / (1 + this.vatRate);
        }
    }

    /**
     * @return the costCenter
     */
    public CostCenterStub getCostCenter() {
        return costCenter;
    }

    /**
     * @param costCenter
     *            the costCenter to set
     */
    public void setCostCenter(final CostCenterStub costCenter) {
        this.costCenter = costCenter;
    }

    /**
     * @return the expseneType
     */
    public ExpenseTypeStub getExpseneType() {
        return expseneType;
    }

    /**
     * @param expseneType
     *            the expseneType to set
     */
    public void setExpseneType(final ExpenseTypeStub expseneType) {
        this.expseneType = expseneType;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

}
