import connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try{
            Connection connection = DatabaseConnection.getInstance().getConnection();
            System.out.println(connection.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}