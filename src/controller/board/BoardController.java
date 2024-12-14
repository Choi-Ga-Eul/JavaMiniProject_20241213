package controller.board;

import model.board.BoardDAO;
import model.board.BoardDTO;

import java.util.ArrayList;

public class BoardController {
    private static BoardController boardController;

    public static BoardController getInstance(){
        if(boardController == null){
            boardController = new BoardController();

        }

        return boardController;
    }

    public int write_board(int user_id, String title, String contents){
        if(title.equals("") || contents.equals("")){
            return 0;
        }

        return BoardDAO.getInstance().write_board(user_id, title, contents);
    }

    public ArrayList<BoardDTO> read_boardList(){


        return BoardDAO.getInstance().read_boardList();
    }

    public BoardDTO check_board(int board_id){
        return BoardDAO.getInstance().getBoard(board_id);
    }

}
