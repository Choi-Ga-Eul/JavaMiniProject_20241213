package model.board;

import connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO {
    private static BoardDAO boardDAO;
    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private BoardDAO(){
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static BoardDAO getInstance(){
        if(boardDAO == null){
            boardDAO = new BoardDAO();
        }

        return boardDAO;
    }

    public int write_board(int user_id, String title, String contents){
        int result = 0;
        String sql = "INSERT INTO board (board_id, title, contents, user_id) VALUES (?, ?, ?, ?)";

        int board_id = read_boardList().size() + 1;

        try{
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, board_id);
            pstmt.setString(2, title);
            pstmt.setString(3, contents);
            pstmt.setInt(4, user_id);

            result = pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage() + "\n in write_board() in BoardDAO");
        }

        return result;
    }

    public ArrayList<BoardDTO> read_boardList(){
        ArrayList<BoardDTO> boardList = new ArrayList<>();
        String sql = "SELECT * FROM board";

        try{
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()){
                BoardDTO boardDTO = new BoardDTO();

                boardDTO.setBoard_id(rs.getInt(1)); //board_id
                boardDTO.setTitle(rs.getString(2)); //title
                boardDTO.setContents(rs.getString(3)); //contetns
                boardDTO.setWrite_date(rs.getString(4));; //write_date
                boardDTO.setView_count(rs.getInt(5)); //view_count
                boardDTO.setUser_id(rs.getInt(6)); //user_id

                boardList.add(boardDTO);
            }

        }catch(SQLException e){
            System.out.println(e.getMessage() + "\n in read_boardList of BoardDAO");
        }

        return boardList;
    }

    public BoardDTO getBoard(int board_id){
        BoardDTO boardDTO = null;
        String sql = "SELECT * FROM board WHERE board_id = ?";

        try{
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, board_id);
            rs = pstmt.executeQuery();

            if(rs.next()){
                boardDTO = new BoardDTO();
                boardDTO.setBoard_id(rs.getInt(1));
                boardDTO.setTitle(rs.getString(2));
                boardDTO.setContents(rs.getString(3));
                boardDTO.setWrite_date(rs.getString(4));
                boardDTO.setView_count(rs.getInt(5));
                boardDTO.setUser_id(rs.getInt(6));

                increase_viewCount(boardDTO.getBoard_id(), boardDTO.getView_count());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n in getBoard() of BoardDAO");
        }


        return boardDTO;
    }// getBoard()

    public void increase_viewCount(int board_id, int view_count){
        String sql = "UPDATE board SET view_count = ? WHERE board_id = ?";

        try{
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, view_count+1);
            pstmt.setInt(2, board_id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n in increase_ViewCount() of BoardDAO");

        }
    }
}
