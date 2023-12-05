package vladyslav.goit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.*;

public class SelectParticipant {

    private static final Logger logger = Logger.getLogger(SelectParticipant.class.getName());
    private int count = 1;

    public void selectParticipant(boolean entry, int second) {
        PropertyConfigurator.configure("log4j.properties");
        String sqlQuery = "SELECT FIO, Email, address, apartament, square\n" +
                "FROM participant_osbb AS PB\n" +
                "JOIN resident AS R ON PB.ID = R.participant_ID\n" +
                "JOIN apartaments AS A ON R.apartament_ID = A.ID\n" +
                "JOIN building_for_apartament AS BA ON A.ID = BA.apartament_ID\n" +
                "JOIN buildings AS B ON BA.building_ID = B.id\n" +
                "WHERE R.entry_for_ap = ? \n" +
                "AND PB.ID IN (\n" +
                "SELECT PB.ID\n" +
                "FROM participant_osbb AS PB\n" +
                "JOIN resident AS R ON PB.ID = R.participant_ID\n" +
                "JOIN apartaments AS A ON R.apartament_ID = A.ID \n" +
                "GROUP BY PB.ID\n" +
                "HAVING COUNT(A.ID) < ?\n" +
                ")\n" +
                "GROUP BY FIO, Email, address, apartament, square\n" +
                "ORDER BY FIO DESC";

        try (java.sql.PreparedStatement preparedStatement = DatabaseConnection.connection.prepareStatement(sqlQuery)) {
            preparedStatement.setBoolean(1, entry);
            preparedStatement.setInt(2, second);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String fio = resultSet.getString("FIO");
                String email = resultSet.getString("Email");
                String adress = resultSet.getString("address");
                String apar = resultSet.getString("apartament");
                String square1 = resultSet.getString("square");
                System.out.println(count + ": ФИО: " + fio + ", Email: " + email + ", Адреса: " + adress + ", Квартира: " + apar + ", Площа: " + square1 + ",");
                logger.info(count + ": ФИО: " + fio + ", Email: " + email + ", Адреса: " + adress + ", Квартира: " + apar + ", Площа: " + square1 + ",");
                count++;
            }
            DatabaseConnection.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            count = 1;
        }
    }
}
