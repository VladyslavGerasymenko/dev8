package vladyslav.goit;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        FlywayExample.flyway();
        DatabaseConnection.getInstance();
        DatabaseConnection.init();
        new SelectParticipant().selectParticipant(false, 2);
    }
}