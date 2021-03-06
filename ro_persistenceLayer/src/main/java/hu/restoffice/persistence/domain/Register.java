package hu.restoffice.persistence.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
 * The persistent class for the register database table.
 *
 */
@Entity
@Table(name = "register")
@NamedQuery(name = "Register.findAll", query = "SELECT r FROM Register r")
public class Register implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "REGISTER_REGISTER_ID_GENERATOR", sequenceName = "REGISTER_REGISTER_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REGISTER_REGISTER_ID_GENERATOR")
    @Column(name = "register_id", updatable = false)
    private Long id;

    @Column(name = "register_no", nullable = false, unique = true, length = 50)
    private String registerNo;

    // uni-directional many-to-one association to RegisterType
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "register_category_id", nullable = false)
    private RegisterCategory registerType;

    @Embedded
    private Effectivity validity;

    public Register() {
    }

    public Long getId() {
        return id;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(final String registerNo) {
        this.registerNo = registerNo;
    }

    public Effectivity getValidity() {
        return validity;
    }

    public RegisterCategory getRegisterType() {
        return registerType;
    }

    public void setRegisterType(final RegisterCategory registerType) {
        this.registerType = registerType;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Register [id=" + id + ", registerNo=" + registerNo + ", registerType=" + registerType + ", validity="
                + validity + "]";
    }

}