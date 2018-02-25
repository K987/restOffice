package hun.restoffice.persistence.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 */
@MappedSuperclass
public abstract class ParameterType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARAMETER_TYPE_ID_GENERATOR")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "description_txt", length = 500)
    private String description;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Embedded
    private Effectivity effectivity;

    public ParameterType() {
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Effectivity getEffectivity() {
        return effectivity;
    }

}
