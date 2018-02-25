package hun.restoffice.persistence.domain;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the job_type database table.
 *
 */
@Entity
@Table(name = "JOB_CATEGORY")
@NamedQuery(name = "JobCategory.findAll", query = "SELECT j FROM JobCategory j")
@SequenceGenerator(name = "PARAMETER_TYPE_ID_GENERATOR", sequenceName = "JOB_CATEGORY_JOB_CATEGORY_ID_SEQ")
@AttributeOverride(name = "id", column = @Column(name = "job_type_id", updatable = false))
public class JobCategory extends ParameterType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "job_hourly_wage_amt", nullable = false, precision = 12, scale = 2)
    private double hourlyWage;

    @Column(name = "job_tip_share_pct", nullable = true, precision = 2, scale = 2)
    private double tipSharePercentage;

    public JobCategory() {
    }

    /**
     * @return the hourlyWage
     */
    public double getHourlyWage() {
        return hourlyWage;
    }

    /**
     * @param hourlyWage
     *            the hourlyWage to set
     */
    public void setHourlyWage(final double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    /**
     * @return the tipSharePercentage
     */
    public double getTipSharePercentage() {
        return tipSharePercentage;
    }

    /**
     * @param tipSharePercentage
     *            the tipSharePercentage to set
     */
    public void setTipSharePercentage(final double tipSharePercentage) {
        this.tipSharePercentage = tipSharePercentage;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "JobCategoryType [hourlyWage=" + hourlyWage + ", tipSharePercentage=" + tipSharePercentage
                + ", parameterType=" + super.toString() + "]";
    }

}