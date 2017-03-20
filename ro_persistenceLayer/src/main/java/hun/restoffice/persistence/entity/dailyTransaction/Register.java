package hun.restoffice.persistence.entity.dailyTransaction;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the registers database table.
 * 
 * @author kalmankostenszky
 */
@Entity
@Table(name = "registers")
@NamedQueries(value = {
		@NamedQuery(name = "Register.findAll", query = "SELECT r FROM Register r"), 
		@NamedQuery(name = Register.READ_BY_ID, query = "SELECT r FROM Register r WHERE LOWER(r.registerId)=:"+Register.ID)
})
public class Register implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String READ_BY_ID = "Register.readById";

	public static final String ID = "id";

	@Id
	@Column(name = "register_id", unique = true, nullable = false, length = 50)
	private String registerId;

	@Enumerated(value = EnumType.ORDINAL)
	@Column(name = "register_type", nullable = false)
	private RegisterType registerType;

	// bi-directional many-to-one association to RegisterClos
	@OneToMany(mappedBy = "register", targetEntity = RegisterClose.class)
	private Set<RegisterClose> registerCloses;

	public Register() {
	}

	public String getRegisterId() {
		return this.registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}

	public RegisterType getRegisterType() {
		return this.registerType;
	}

	public void setRegisterType(RegisterType registerType) {
		this.registerType = registerType;
	}

	public Set<RegisterClose> getRegisterCloses() {
		return this.registerCloses;
	}

	public void setRegisterCloses(Set<RegisterClose> registerCloses) {
		this.registerCloses = registerCloses;
	}

	public RegisterClose addRegisterClose(RegisterClose registerClos) {
		getRegisterCloses().add(registerClos);
		registerClos.setRegister(this);

		return registerClos;
	}

	public RegisterClose removeRegisterClose(RegisterClose registerClos) {
		getRegisterCloses().remove(registerClos);
		registerClos.setRegister(null);

		return registerClos;
	}

}