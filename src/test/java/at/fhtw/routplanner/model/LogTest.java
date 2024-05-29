package at.fhtw.routplanner.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LogTest {
    //LogTests
    @Test
    public void testLogConstructorAndGetter() {
        LocalDate date = LocalDate.now();
        Log log = new Log(232L, date, "Test Comment", 123456789, 5, 12.5, 3.5, 10);

        assertEquals("1", log.getLogId());
        assertEquals(date, log.getDate());
        assertEquals("Test Comment", log.getComment());
        assertEquals(Integer.valueOf(123456789), log.getTimeStamp());
        assertEquals(Integer.valueOf(5), log.getDifficulty());
        assertEquals(Float.valueOf(12.5f), log.getDistance());
        assertEquals(Float.valueOf(3.5f), log.getTimeLength());
        assertEquals(Integer.valueOf(10), log.getRating());
    }

    @Test
    public void testLogEquals() {
        LocalDate date = LocalDate.now();
        Log log1 = new Log(23L, date, "Test Comment", 123456789, 5, 12.5, 3.5, 10);
        Log log2 = new Log(24L, date, "Test Comment", 123456789, 5, 12.5, 3.5, 10);
        assertEquals(log1, log2);
    }

    @Test
    public void testLogNotEquals() {
        LocalDate date = LocalDate.now();
        Log log1 = new Log(23L, date, "Test Comment", 123456789, 5, 12.5, 3.5, 10);
        Log log2 = new Log(32L, date, "Different Comment", 987654321, 3, 10.0, 2.5, 8);

        assertNotEquals(log1, log2);
    }

    @Test
    public void testLogToString() {
        LocalDate date = LocalDate.of(2023, 4, 1);
        Log log = new Log(23L, date, "Test Comment", 123456789, 5, 12.5, 3.5, 10);
        String expectedToStringContains = "[id=1, date=2023-04-01, comment=Test Comment, timeStamp=123456789, difficulty=5, distance=12.5, timeLength=3.5, rating=10]";

        // Ensure the toString method contains some key identifiers; exact format can vary
        String logToString = log.toString();
        assert(logToString.contains("id=1"));
        assert(logToString.contains("date=2023-04-01"));
        assert(logToString.contains("comment=Test Comment"));
        assert(logToString.contains("timeStamp=123456789"));
        assert(logToString.contains("difficulty=5"));
        assert(logToString.contains("distance=12.5"));
        assert(logToString.contains("timeLength=3.5"));
        assert(logToString.contains("rating=10"));
    }
}