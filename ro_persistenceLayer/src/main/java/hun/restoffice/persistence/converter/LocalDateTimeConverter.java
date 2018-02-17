package hun.restoffice.persistence.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 */
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    /*
     * (non-Javadoc)
     *
     * @see javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang.
     * Object)
     */
    @Override
    public Timestamp convertToDatabaseColumn(final LocalDateTime attribute) {
        return (attribute == null ? null : Timestamp.valueOf(attribute));
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang.
     * Object)
     */
    @Override
    public LocalDateTime convertToEntityAttribute(final Timestamp dbData) {
        return (dbData == null ? null : dbData.toLocalDateTime());
    }

}
