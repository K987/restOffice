package hun.restoffice.persistence.entity.dailyTransaction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the register_closes database table.
 * 
 * @author kalmankostenszky
 */
@Entity
@Table(name = "register_closes")
@NamedQueries( value = { 
		@NamedQuery(name = "RegisterClose.findAll", query = "SELECT r FROM RegisterClose r"),
		@NamedQuery(name = RegisterClose.FIND_REGISTER_CLOSE, query = "SELECT DISTINCT r FROM RegisterClose r JOIN FETCH r.register WHERE r.registerCloseDate =:"+RegisterClose.DAY),
		@NamedQuery(name = RegisterClose.FIND_LAST_CLOSE, query = "SELECT r0 FROM RegisterClose r0 JOIN FETCH r0.register WHERE "
				+ "r0.registerCloseDate = "
				+ "(SELECT MAX(r1.registerCloseDate) FROM RegisterClose r1 WHERE r1.id.registerCloseRegisterId = r0.id.registerCloseRegisterId) "
				+ "AND r0.id.registerCloseNo = "
				+ "(SELECT MAX(r2.id.registerCloseNo) FROM RegisterClose r2 WHERE r2.id.registerCloseRegisterId = r0.id.registerCloseRegisterId)")
		})
public class RegisterClose implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_REGISTER_CLOSE = "RegisterClose.findRegisterClose";
	public static final String FIND_LAST_CLOSE = "RegisterClose.findLastClose";

	public static final String DAY = "day";

	@EmbeddedId
	private RegisterCloseId id;

	@Column(name = "register_close_amt", precision = 131089)
	private BigDecimal registerCloseAmt;

	@Temporal(TemporalType.DATE)
	@Column(name = "register_close_date", nullable = false)
	private Date registerCloseDate;

	// bi-directional many-to-one association to Register
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "register_close_register_id", referencedColumnName = "register_id", nullable = false, insertable = false, updatable = false)
	private Register register;

	public RegisterClose() {
	}


	/**
	 * @param registerId
	 * @param closeAmt
	 * @param time
	 * @param closeNo
	 */
	public RegisterClose(String registerId, BigDecimal closeAmt, Date time, int closeNo) {
		this.id = new RegisterCloseId(registerId, closeNo);
		this.registerCloseDate = time;
		this.registerCloseAmt = closeAmt;
	}

	public RegisterCloseId getId() {
		return this.id;
	}

	public void setId(RegisterCloseId id) {
		this.id = id;
	}

	public BigDecimal getRegisterCloseAmt() {
		return this.registerCloseAmt;
	}

	public void setRegisterCloseAmt(BigDecimal registerCloseAmt) {
		this.registerCloseAmt = registerCloseAmt;
	}

	public Date getRegisterCloseDate() {
		return this.registerCloseDate;
	}

	public void setRegisterCloseDate(Date registerCloseDate) {
		this.registerCloseDate = registerCloseDate;
	}

	public Register getRegister() {
		return this.register;
	}

	public void setRegister(Register register) {
		this.register = register;
	}

}