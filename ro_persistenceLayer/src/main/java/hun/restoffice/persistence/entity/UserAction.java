package hun.restoffice.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 */
@Embeddable
public class UserAction {

    @Column(name = "create_dttm", nullable = false, updatable = false)
    private LocalDateTime created;

    @Column(name = "deleted_dttm")
    private LocalDateTime deleted;

    @Column(name = "last_updated_dttm")
    private LocalDateTime lastUpdated;

    // uni-directional many-to-one association to RoUser
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_user_id", nullable = false)
    private User createUser;

    // uni-directional many-to-one association to RoUser
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delete_user_id")
    private User deleteUser;

    // uni-directional many-to-one association to RoUser
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_update_user_id")
    private User lastUpdateUser;

    /**
     * @return the created
     */
    public LocalDateTime getCreated() {
        return created;
    }

    /**
     * @param created
     *            the created to set
     */
    protected void setCreated(final LocalDateTime created) {
        this.created = created;
    }

    /**
     * @return the deleted
     */
    public LocalDateTime getDeleted() {
        return deleted;
    }

    /**
     * @param deleted
     *            the deleted to set
     */
    protected void setDeleted(final LocalDateTime deleted) {
        this.deleted = deleted;
    }

    /**
     * @return the lastUpdated
     */
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    /**
     * @param lastUpdated
     *            the lastUpdated to set
     */
    protected void setLastUpdated(final LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * @return the createUser
     */
    public User getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser
     *            the createUser to set
     */
    protected void setCreateUser(final User createUser) {
        this.createUser = createUser;
    }

    /**
     * @return the deleteUser
     */
    public User getDeleteUser() {
        return deleteUser;
    }

    /**
     * @param deleteUser
     *            the deleteUser to set
     */
    protected void setDeleteUser(final User deleteUser) {
        this.deleteUser = deleteUser;
    }

    /**
     * @return the lastUpdateUser
     */
    public User getLastUpdateUser() {
        return lastUpdateUser;
    }

    /**
     * @param lastUpdateUser
     *            the lastUpdateUser to set
     */
    protected void setLastUpdateUser(final User lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserAction [created=" + created + ", deleted=" + deleted + ", lastUpdated=" + lastUpdated
                + ", createUser=" + createUser + ", deleteUser=" + deleteUser + ", lastUpdateUser=" + lastUpdateUser
                + "]";
    }
}
