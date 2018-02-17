package hun.restoffice.persistence.converter;

import java.sql.Time;
import java.time.LocalTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 */
@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, Time> {

    /*
     * (non-Javadoc)
     *
     * @see javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang.
     * Object)
     */
    @Override
    public Time convertToDatabaseColumn(final LocalTime attribute) {
        return (attribute == null ? null : Time.valueOf(attribute));
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang.
     * Object)
     */
    @Override
    public LocalTime convertToEntityAttribute(final Time dbData) {
        return (dbData == null ? null : dbData.toLocalTime());

    }

}
