package com.example.kursova.utils;

import org.junit.jupiter.api.Test;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoggerUtilTest {

    @Test
    void testGetLogger() {
        Logger logger = LoggerUtil.getLogger();
        assertNotNull(logger);
        assertEquals("TourAppLogger", logger.getName());
    }

    @Test
    void testLoggerHandlers() {
        Logger logger = LoggerUtil.getLogger();
        Handler[] handlers = logger.getHandlers();
        assertTrue(handlers.length > 0, "Logger should have at least one handler");
    }

    @Test
    void testLoggingLevel() {
        Logger logger = LoggerUtil.getLogger();
        assertEquals(Level.ALL, logger.getLevel());
    }

    @Test
    void testLogging() {
        Logger logger = LoggerUtil.getLogger();
        TestHandler testHandler = new TestHandler();
        logger.addHandler(testHandler);

        String testMessage = "Test log message";
        logger.info(testMessage);

        List<LogRecord> records = testHandler.getRecords();
        assertFalse(records.isEmpty());
        assertEquals(testMessage, records.get(0).getMessage());
        assertEquals(Level.INFO, records.get(0).getLevel());
    }

    private static class TestHandler extends Handler {
        private final List<LogRecord> records = new ArrayList<>();

        @Override
        public void publish(LogRecord record) {
            records.add(record);
        }

        @Override
        public void flush() {
        }

        @Override
        public void close() throws SecurityException {
        }

        public List<LogRecord> getRecords() {
            return records;
        }
    }
}