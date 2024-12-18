package model.comment;

import connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentDAO {
    private static CommentDAO commentDAO;
    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private CommentDAO(){
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static CommentDAO getInstance(){
        if(commentDAO == null){
            commentDAO = new CommentDAO();

        }

        return commentDAO;
    }

    public int write_comment(int user_id, int board_id, String comment){
        int result = 0;
        String sql = "INSERT INTO comment (comment, user_id, board_id) VALUES (?, ?, ?)";

        try{
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, comment);
            pstmt.setInt(2, user_id);
            pstmt.setInt(3, board_id);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n in write_comment() of CommentDAO");
        }

        return result;
    }

    public ArrayList<CommentDTO> read_commentList(int board_id){
        ArrayList<CommentDTO> commentList = new ArrayList<>();
        String sql = "SELECT * FROM comment WHERE board_id = ?";

        try{
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, board_id);
            rs = pstmt.executeQuery();

            while(rs.next()){
                CommentDTO commentDTO = new CommentDTO();

                commentDTO.setComment_id(rs.getInt(1));
                commentDTO.setComment(rs.getString(2));
                commentDTO.setWrite_date(rs.getString(3));
                commentDTO.setUser_id(rs.getInt(4));
                commentDTO.setBoard_id(rs.getInt(5));

                commentList.add(commentDTO);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage() + "\n in read_commentList() of CommentDAO");
        }

        return commentList;
    }
}
