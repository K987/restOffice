package hu.restoffice.persistence.converter;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    /*
     * (non-Javadoc)
     *
     * @see javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang.
     * Object)
     */
    @Override
    public Date convertToDatabaseColumn(final LocalDate attribute) {
        return (attribute == null ? null : Date.valueOf(attribute));
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang.
     * Object)
     */
    @Override
    public LocalDate convertToEntityAttribute(final Date dbData) {
        return (dbData == null ? null : dbData.toLocalDate());
    }

}
