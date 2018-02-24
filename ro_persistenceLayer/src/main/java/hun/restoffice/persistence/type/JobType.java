package hun.restoffice.persistence.type;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import hun.restoffice.persistence.util.ParameterType;


/**
 * The persistent class for the job_type database table.
 *
 */
@Entity
@Table(name="job_type")
@NamedQuery(name="JobType.findAll", query="SELECT j FROM JobType j")
@SequenceGenerator(name = "PARAMETER_TYPE_ID_GENERATOR", sequenceName = "JOB_TYPE_JOB_TYPE_ID_SEQ")
@AttributeOverride(name = "id", column = @Column(name = "job_type_id", updatable = false))
public class JobType extends ParameterType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name="job_hourly_wage_amt", nullable=false, precision=12, scale=2)
    private double jobHourlyWageAmt;

    @Column(name = "job_tip_share_pct", nullable = true, precision = 2, scale = 2)
    private double jobTipSharePct;

    public JobType() {
    }

    public double getJobHourlyWageAmt() {
        return jobHourlyWageAmt;
    }

    public void setJobHourlyWageAmt(final double jobHourlyWageAmt) {
        this.jobHourlyWageAmt = jobHourlyWageAmt;
    }

    public double getJobTipSharePct() {
        return jobTipSharePct;
    }

    public void setJobTipSharePct(final double jobTipSharePct) {
        this.jobTipSharePct = jobTipSharePct;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "JobType [jobHourlyWageAmt=" + jobHourlyWageAmt + ", jobTipSharePct=" + jobTipSharePct + ", getId()="
                + this.getId() + ", getDescription()=" + this.getDescription() + ", getName()=" + this.getName()
                + ", getEffectivity()=" + this.getEffectivity() + "]";
    }


}