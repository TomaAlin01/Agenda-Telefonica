package agenda_telefonica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/agenda";
        String user = "root";
        String password = "123456";
        
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the MySQL server successfully.");
        } catch (SQLException e) {
            System.out.println("MySQL connection failed!");
            e.printStackTrace();
        }
    }
}
