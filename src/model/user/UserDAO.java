package model.user;

import connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static UserDAO userDAO;
    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private UserDAO(){
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static UserDAO getInstance(){
        if(userDAO == null){
            userDAO = new UserDAO();
        }

        return userDAO;
    }

    public boolean idCheck(String id){
        boolean result = false;
        String sql = "SELECT * FROM user WHERE id = ?";

        try{
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, id);

            rs = pstmt.executeQuery();

            if(rs.next()){
                result = true;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage() + "\nin idCheck of UserDAO");
        }

        return result;
    }

    public int insertUser(String id, String password, String userName){
        String sql = "INSERT INTO user (name, id, password ) VALUES (?, ?, ?)";
        int result = 0;

        try{
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, userName);
            pstmt.setString(2, id);
            pstmt.setString(3, password);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\nin insertUser of UserDAO");
        }

        return result;
    }

    public UserDTO getUser(String id, String password){
        UserDTO userDTO = null;
        String sql = "SELECT * FROM user WHERE id = ? and password = ?";

        try{
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();

            if (rs.next()){
                int user_id = rs.getInt(1);
                String userName = rs.getString(2);
                id = rs.getString(3);
                password = rs.getString(4);

                userDTO = new UserDTO(user_id, userName, id, password);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\nin getUser of UserDAO");
        }

        return userDTO;
    }
}
