package model.board;

public class BoardDTO {
    private int board_id;
    private String title;
    private String contents;
    private String write_date;
    private int view_count;
    private int user_id;

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getWrite_date() {
        return write_date;
    }

    public void setWrite_date(String write_date) {
        this.write_date = write_date;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "BoardDTO{" +
                "board_id=" + board_id +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", write_date='" + write_date + '\'' +
                ", view_count=" + view_count +
                ", user_id=" + user_id +
                '}';
    }
}
