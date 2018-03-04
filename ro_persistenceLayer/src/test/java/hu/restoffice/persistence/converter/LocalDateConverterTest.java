package hu.restoffice.persistence.converter;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Test;

/**
 *
 */
public class LocalDateConverterTest {

    LocalDateConverter converter = new LocalDateConverter();

    @Test
    public void convertToDatabaseColumnTest() {
        Date date = converter.convertToDatabaseColumn(LocalDate.of(2018, 01, 30));
        assertEquals("2018-01-30", date.toString());
    }

    @Test
    public void convertToEntityAttributeTest() {
        LocalDate date = converter.convertToEntityAttribute(Date.valueOf("2018-01-30"));
        assertEquals("2018-01-30", date.toString());
    }
}
