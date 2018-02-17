package hun.restoffice.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name="transition")
@NamedQuery(name="Transition.findAll", query="SELECT t FROM Transition t")
public class Transition implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="TRANSITION_TRANSITIONID_GENERATOR", sequenceName="TRANSITION_TRANSITION_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRANSITION_TRANSITIONID_GENERATOR")
    @Column(name = "transition_id", updatable = false)
    private Long id;

    @Column(name="transition_amt", nullable=false, precision=12, scale=2)
    private BigDecimal transitionAmt;

    @Column(name="transition_dttm", nullable=false)
    private LocalDateTime performedDateTime;

    //bi-directional many-to-one association to DepositBox
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="from_deposit_box_id", nullable=false)
    private DepositBox from;

    //bi-directional many-to-one association to DepositBox
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="to_deposit_box_id", nullable=false)
    private DepositBox to;

    //uni-directional many-to-one association to RoUser
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User createdBy;

    public Transition() {
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getTransitionAmt() {
        return transitionAmt;
    }

    public void setTransitionAmt(final BigDecimal transitionAmt) {
        this.transitionAmt = transitionAmt;
    }

    public LocalDateTime getPerformedDateTime() {
        return performedDateTime;
    }

    public void setPerformedDateTime(final LocalDateTime transitionDttm) {
        performedDateTime = transitionDttm;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Transition [id=" + id + ", transitionAmt=" + transitionAmt + ", performedDateTime=" + performedDateTime
                + ", from=" + from + ", to=" + to + ", createdBy=" + createdBy + "]";
    }

    public void setRoUser(final User roUser) {
        createdBy = roUser;
    }

}