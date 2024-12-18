package controller.comment;

import model.comment.CommentDAO;
import model.comment.CommentDTO;

import java.util.ArrayList;

public class CommentController {
    private static CommentController commentController;

    public static CommentController getInstance(){
        if(commentController == null){
            commentController = new CommentController();
        }

        return commentController;
    }

    public int write_comment(int user_id, int board_id, String comment){
        return CommentDAO.getInstance().write_comment(user_id, board_id, comment);
    }

    public ArrayList<CommentDTO> read_commentList(int board_id){
        return CommentDAO.getInstance().read_commentList(board_id);
    }
}
