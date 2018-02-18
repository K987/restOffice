package hun.restoffice.persistence.type;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import hun.restoffice.persistence.util.ParameterType;


/**
 * The persistent class for the document_type database table.
 *
 */
@Entity
@Table(name="document_type")
@NamedQuery(name="DocumentType.findAll", query="SELECT d FROM DocumentType d")
@SequenceGenerator(name = "PARAMETER_TYPE_ID_GENERATOR", sequenceName = "DOCUMENT_TYPE_DOCUMENT_TYPE_ID_SEQ")
@AttributeOverride(name = "id", column = @Column(name = "document_type_id", updatable = false))
public class Obsolete_DocumentType extends ParameterType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name="document_type_type_cd", nullable=false)
    @Enumerated(EnumType.ORDINAL)
    private DocumentTypes typeCd;

    public Obsolete_DocumentType() {
    }

    public DocumentTypes getTypeCd() {
        return typeCd;
    }

    public void setTypeCd(final DocumentTypes documentTypeTypeCd) {
        typeCd = documentTypeTypeCd;
    }

}