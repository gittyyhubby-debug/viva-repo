package com.example.util;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DBConnTest {

    @Test
    void testGetConnectionNotNull() {
        try (Connection conn = DBConn.getConnection()) {
            assertNotNull(conn, "Connection should not be null");
            assertFalse(conn.isClosed(), "Connection should be open");
        } catch (SQLException e) {
            fail("Should have obtained a valid connection, but got: " + e.getMessage());
        }
    }

    @Test
    void testConnectionIsValid() {
        try (Connection conn = DBConn.getConnection()) {
            assertTrue(conn.isValid(2), "Connection should be valid within 2 seconds");
        } catch (SQLException e) {
            fail("Database connection is not valid: " + e.getMessage());
        }
    }
}
