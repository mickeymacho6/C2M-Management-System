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
        String connectionUrl = "jdbc:sqlserver:greenhornetscard2manage.database.windows.net;databaseName=Green Hornets Card 2 Manage";
        String username = "greenhornetsadmin";
        String password = "GreenHornetsUp!";

        /**
         * Declare the JDBC object.
         */
        Connection connection = null;

        try {
            //Establish the connection.
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            connection = DriverManager.getConnection(connectionUrl, username, password);
            System.out.println("Connected to SQL Server");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
