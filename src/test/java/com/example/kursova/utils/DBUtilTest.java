package com.example.kursova.utils;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

class DBUtilTest {

    @Test
    void testGetConnection() {
        try {
            Connection conn = DBUtil.getConnection();
            assertNotNull(conn);
            assertFalse(conn.isClosed());
            conn.close();
        } catch (Exception e) {
            fail("Failed to get database connection: " + e.getMessage());
        }
    }

    @Test
    void testConnectionIsValid() {
        try {
            Connection conn = DBUtil.getConnection();
            assertTrue(conn.isValid(5)); // Check if connection is valid with 5 second timeout
            conn.close();
        } catch (Exception e) {
            fail("Connection validation failed: " + e.getMessage());
        }
    }
}