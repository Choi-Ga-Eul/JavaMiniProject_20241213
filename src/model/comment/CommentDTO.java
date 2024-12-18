package model.comment;

public class CommentDTO {
    private int comment_id;
    private String comment;
    private String write_date;
    private int user_id;
    private int board_id;


    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getWrite_date() {
        return write_date;
    }

    public void setWrite_date(String write_date) {
        this.write_date = write_date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }


    @Override
    public String toString() {
        return "CommentDTO{" +
                "comment_id=" + comment_id +
                ", comment='" + comment + '\'' +
                ", write_date='" + write_date + '\'' +
                ", user_id=" + user_id +
                ", board_id=" + board_id +
                '}';
    }
}
