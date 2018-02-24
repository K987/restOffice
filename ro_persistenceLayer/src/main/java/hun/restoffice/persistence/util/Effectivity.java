package hun.restoffice.persistence.util;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import hun.restoffice.persistence.entity.User;

/**
 *
 */
@Embeddable
public class Effectivity {

    @Transient
    private Boolean effective;

    @Column(name = "valid_from_dttm", nullable = false)
    private LocalDateTime validFrom;

    @Column(name = "valid_to_dttm", nullable = false)
    private LocalDateTime validTo;

    @Column(name = "create_user_id", nullable = false, updatable = false)
    private User creator;

    @Column(name = "last_update_user_id")
    private User lastUpdateUser;

    public Effectivity() {
    }

    public Boolean isEffective() {
        if (effective == null)
            updateValidity();
        return effective;
    }


    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(final LocalDateTime validFrom) {
        this.validFrom = validFrom;
        updateValidity();
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }

    protected void setValidTo(final LocalDateTime validTo) {
        this.validTo = validTo;
        updateValidity();
    }

    /**
     *
     */
    private void updateValidity() {
        LocalDateTime now = LocalDateTime.now() ;
        effective = (validFrom.isBefore(now) && validTo.isAfter(now));
    }



    /**
     * @return the creator
     */
    public User getCreator() {
        return creator;
    }

    /**
     * @param creator the creator to set
     */
    protected void setCreator(final User creator) {
        this.creator = creator;
    }

    /**
     * @return the lastUpdateUser
     */
    public User getLastUpdateUser() {
        return lastUpdateUser;
    }

    /**
     * @param lastUpdateUser the lastUpdateUser to set
     */
    public void setLastUpdateUser(final User lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Effectivity [effective=" + effective + ", validFrom=" + validFrom + ", validTo=" + validTo
                + ", creator=" + creator + ", lastUpdateUser=" + lastUpdateUser + "]";
    }
}
