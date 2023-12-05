package vladyslav.goit;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnection {
    private static DatabaseConnection dbc;
    private static final String PROPERTIES_FILE = "database.properties";
    static Connection connection;
    static Properties properties = new Properties();

    public static synchronized DatabaseConnection getInstance() {
        if (dbc == null) {
            dbc = new DatabaseConnection();
        }
        return dbc;
    }

    public static void loadProperties() {
        try (FileInputStream fls = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(fls);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void init() throws SQLException {
        loadProperties();
        connection = DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.userName"),
                properties.getProperty("db.password"));
    }
}