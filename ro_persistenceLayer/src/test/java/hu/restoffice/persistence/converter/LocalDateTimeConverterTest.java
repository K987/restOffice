package hu.restoffice.persistence.converter;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.Test;

/**
 *
 */
public class LocalDateTimeConverterTest {

    LocalDateTimeConverter converter = new LocalDateTimeConverter();

    @Test
    public void convertToDatabaseColumnTest() {
        Timestamp date = converter.convertToDatabaseColumn(LocalDateTime.of(2018, 01, 30, 23, 01, 01, 999));
        assertEquals("2018-01-30 23:01:01.000000999", date.toString());
    }

    @Test
    public void convertToEntityAttributeTest() {
        LocalDateTime date = converter.convertToEntityAttribute(Timestamp.valueOf("2018-01-30 23:01:01.000000999"));
        assertEquals("2018-01-30T23:01:01.000000999", date.toString());
    }
}
