package vladyslav.goit;

import org.flywaydb.core.Flyway;

public class FlywayExample {

    public static void flyway() {
        DatabaseConnection.loadProperties();
        Flyway.configure()
                .dataSource(DatabaseConnection.properties.getProperty("db.url"),
                            DatabaseConnection.properties.getProperty("db.userName"),
                            DatabaseConnection.properties.getProperty("db.password"))
                .locations("classpath:databaseFlyway/migration")
                .load()
                .migrate();
    }
}
