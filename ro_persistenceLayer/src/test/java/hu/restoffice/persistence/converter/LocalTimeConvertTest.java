package hu.restoffice.persistence.converter;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.time.LocalTime;

import org.junit.Test;

/**
 *
 */
public class LocalTimeConvertTest {

    LocalTimeConverter converter = new LocalTimeConverter();

    @Test
    public void convertToDatabaseColumnTest() {
        Time date = converter.convertToDatabaseColumn(LocalTime.of(23, 01, 01));
        assertEquals("23:01:01", date.toString());
    }

    @Test
    public void convertToEntityAttributeTest() {
        LocalTime date = converter.convertToEntityAttribute(Time.valueOf("23:01:01"));
        assertEquals("23:01:01", date.toString());
    }
}
