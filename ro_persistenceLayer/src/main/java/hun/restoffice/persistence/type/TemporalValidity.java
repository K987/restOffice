package hun.restoffice.persistence.type;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

/**
 *
 */
@Embeddable
public class TemporalValidity {

    @Transient
    private Boolean valid;

    @Column(name = "valid_from_dttm", nullable = false)
    private LocalDateTime validFrom;

    @Column(name = "valid_to_dttm", nullable = false)
    private LocalDateTime validTo;

    public TemporalValidity() {
    }

    public Boolean isValid() {
        if (valid == null)
            updateValidity();
        return valid;
    }

    // public void setValid(final Boolean valid) {
    // this.valid = valid;
    // }

    public LocalDateTime getvalidFrom() {
        return validFrom;
    }

    public void setValidFromDttm(final LocalDateTime validFrom) {
        this.validFrom = validFrom;
        updateValidity();
    }

    public LocalDateTime getValidToDttm() {
        return validTo;
    }

    public void setValidToDttm(final LocalDateTime validTo) {
        this.validTo = validTo;
        updateValidity();
    }

    /**
     *
     */
    private void updateValidity() {
        LocalDateTime now = LocalDateTime.now() ;
        valid = (validFrom.isBefore(now) && validTo.isAfter(now));

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TemporalValidity [valid=" + valid + ", validFrom=" + validFrom + ", validTo=" + validTo + "]";
    }

}
