package hu.restoffice.persistence.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

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

/**
 * The persistent class for the transition database table.
 *
 */
@Entity
@Table(name = "transition")
@NamedQuery(name = "Transition.findAll", query = "SELECT t FROM Transition t")
public class Transition implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TRANSITION_TRANSITION_ID_GENERATOR", sequenceName = "TRANSITION_TRANSITION_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSITION_TRANSITION_ID_GENERATOR")
    @Column(name = "transition_id", updatable = false)
    private Long id;

    @Column(name = "transition_amt", nullable = false, updatable = false, precision = 12, scale = 2)
    private double amount;

    @Column(name = "description_txt", length = 500, nullable = false)
    private String description;

    @Column(name = "transition_dttm", updatable = false, nullable = false)
    private LocalDateTime performedAt;

    // bi-directional many-to-one association to DepositBox
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_deposit_box_id", updatable = false, nullable = false)
    private DepositBox from;

    // bi-directional many-to-one association to DepositBox
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_deposit_box_id", updatable = false, nullable = false)
    private DepositBox to;

    // uni-directional many-to-one association to RoUser
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false, nullable = false)
    private User createdBy;

    public Transition() {
    }

    public Long getId() {
        return id;
    }

    protected void setId(final Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(final double transitionAmt) {
        amount = transitionAmt;
    }

    public LocalDateTime getPerformedAt() {
        return performedAt;
    }

    public void setPerformedAt(final LocalDateTime transitionDttm) {
        performedAt = transitionDttm;
    }

    public DepositBox getFrom() {
        return from;
    }

    public void setFrom(final DepositBox fromBox) {
        from = fromBox;
    }

    public DepositBox getTo() {
        return to;
    }

    public void setTo(final DepositBox toBox) {
        to = toBox;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final User roUser) {
        createdBy = roUser;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Transition [id=" + id + ", amount=" + amount + ", description=" + description + ", performedAt="
                + performedAt + ", from=" + from + ", to=" + to + ", createdBy=" + createdBy + "]";
    }

}