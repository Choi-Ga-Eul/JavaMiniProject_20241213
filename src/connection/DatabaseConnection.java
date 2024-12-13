package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection databaseConnection;
    private Connection connection;

    public static DatabaseConnection getInstance(){
        if(databaseConnection == null){
            databaseConnection = new DatabaseConnection();
        }

        return databaseConnection;
    }

    public Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/javaminiproject_20241213";
        String user = "root";
        String password = "5709";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage() + "\n in DatabaseConnection");
        }

        return connection;
    }

}
