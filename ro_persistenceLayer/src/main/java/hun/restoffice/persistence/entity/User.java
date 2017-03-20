package hun.restoffice.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the users database table.
 * 
 * @author kalmankostenszky
 */
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id", length = 50)
	private String id;

	@Column(name = "user_name", length = 100)
	private String name;

	@Column(name = "user_pwd", nullable = false, length = 50)
	private String pwd;

	// TODO: add not nullabe to DB, maybe enum
	@Column(name = "user_role", nullable = false, length = 50)
	private String role;

	public User() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String userId) {
		this.id = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String userName) {
		this.name = userName;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String userPwd) {
		this.pwd = userPwd;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String userRole) {
		this.role = userRole;
	}
}