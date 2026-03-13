package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseManager handles creation and management of PostgreSQL database connections.
 * Connections are created on demand and should be closed by callers
 */
public class DatabaseManager {

    private static final String URL =
            "jdbc:postgresql://localhost:6666/radiation_monitor_db";
    private static final String USER     = "postgres";
    private static final String PASSWORD = "mammamia";

    /**
     * Load the JDBC driver once when the class is first used.
     */
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(
                    "PostgreSQL JDBC Driver not found on classpath.\n" +
                            "Make sure org.postgresql:postgresql is in your pom.xml.", e);
        }
    }

    /**
     * Opens and returns a new database connection.
     * Callers MUST close this connection (use try-with-resources)
     * @return a live Connection to the configured PostgreSQL database
     * @throws SQLException if the connection cannot be established
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Convenience method: test whether a connection can be established.
     * Useful for a startup health-check.
     */
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}