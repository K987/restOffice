package hun.restoffice.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the ro_user database table.
 *
 */
@Entity
@Table(name="ro_user")
@NamedQuery(name="RoUser.findAll", query="SELECT r FROM RoUser r")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="RO_USER_USERID_GENERATOR", sequenceName="RO_USER_USER_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RO_USER_USERID_GENERATOR")
    @Column(name = "user_id", updatable = false)
    private Long id;

    public User() {
    }

    public Long getId() {
        return id;
    }

}