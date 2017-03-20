package hun.restoffice.persistence.entity.dailyTransaction;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the register_closes database table.
 * 
 * @author kalmankostenszky
 */
@Embeddable
public class RegisterCloseId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "register_close_register_id", unique = true, nullable = false, length = 50)
	private String registerCloseRegisterId;

	@Column(name = "register_close_no", unique = true, nullable = false)
	private Integer registerCloseNo;

	public RegisterCloseId() {
	}

	/**
	 * @param registerId
	 * @param closeNo
	 */
	public RegisterCloseId(String registerId, int closeNo) {
		this.registerCloseRegisterId = registerId;
		this.registerCloseNo = closeNo;
	}

	public String getRegisterCloseRegisterId() {
		return this.registerCloseRegisterId;
	}

	public void setRegisterCloseRegisterId(String registerCloseRegisterId) {
		this.registerCloseRegisterId = registerCloseRegisterId;
	}

	public Integer getRegisterCloseNo() {
		return this.registerCloseNo;
	}

	public void setRegisterCloseNo(Integer registerCloseNo) {
		this.registerCloseNo = registerCloseNo;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RegisterCloseId)) {
			return false;
		}
		RegisterCloseId castOther = (RegisterCloseId) other;
		return this.registerCloseRegisterId.equals(castOther.registerCloseRegisterId) && this.registerCloseNo.equals(castOther.registerCloseNo);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.registerCloseRegisterId.hashCode();
		hash = hash * prime + this.registerCloseNo.hashCode();

		return hash;
	}
}