import java.sql.*;

/**
 * Establishing the connection between the app and the Azure database.
 * @author Harlan Nguyen
 * Date: 03/05/2022
 */
public class AzureSQLConnection {
    public static void main(String[] args) {
        /**
         * setting the parameters.
         */
        String connectionUrl = "jdbc:sqlserver:// ;databaseName=Green Hornets Card 2 Manage";
        String username = "";
        String password = "";

        /**
         * Declare the JDBC object.
         */
        Connection connection = null;

        try {
            //Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connectionUrl, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
