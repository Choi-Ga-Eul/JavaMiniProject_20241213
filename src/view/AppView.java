package view;

import controller.board.BoardController;
import controller.comment.CommentController;
import controller.user.UserController;
import model.board.BoardDTO;
import model.comment.CommentDTO;
import model.user.UserDTO;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.Scanner;

public class AppView {
    Scanner scan = new Scanner(System.in);
    UserDTO user;

    public void pageRun(){
        while(true){
            System.out.println("1.로그인\n2.회원가입\n3.종료\n");
            System.out.print("입력 >>> ");

            int choice = scan.nextInt();

            if(choice == 3) {
                System.out.println("프로그램을 종료합니다...");
                break;
            }else{
                switch(choice){
                    case 1:
                        sign_in();

                        if(user != null){

                            read_boardList();

                            while(true){
                                System.out.println("1.게시물 작성\n2.게시물 조회\n3.게시물 댓글 작성\n4.로그아웃");
                                System.out.print("입력 >>> ");

                                choice = scan.nextInt();

                                if(choice == 4){
                                    logout();
                                    System.out.println("로그아웃");
                                    break;
                                }
                                else{
                                    switch(choice) {
                                        case 1: //게시물 작성
                                            write_board();
                                            break;
                                        case 2: // 게시물 조회
                                            check_board(user.getUser_id());
                                            break;
                                        case 3: // 게시물 댓글 작성
                                            write_boardComment(user.getUser_id());
                                            break;
                                        default:
                                            System.out.println("메뉴에 있는 번호를 입력해주세요");
                                            break;
                                    }
                                }
                            }
                        }

                        break;
                    case 2:
                        sign_up();
                        break;
                    default:
                        System.out.println("메뉴에 있는 번호를 입력해주세요");
                        break;
                }
            }
        }// Enf of While
    }//End of pageRun()

    public void sign_in(){ // 로그인
        System.out.print("ID 입력 >>> ");
        String id = scan.next();

        System.out.print("Password 입력 >>> ");
        String password = scan.next();

        user = UserController.getInstance().sign_in(id, password);

        if(user != null){
            System.out.println("로그인 성공");
        }else{
            System.out.println("로그인 실패");
        }
    }// sign_in()

    public void sign_up(){ // 회원 가입
        System.out.print("ID 입력 >>> ");
        String id = scan.next();

        System.out.print("Password 입력 >>> ");
        String password = scan.next();

        System.out.print("이름 입력 >>> ");
        String userName = scan.next();

        int result = UserController.getInstance().sign_up(id, password, userName);

        switch(result){
            case 0:
                System.out.println("회원가입 실패");
                break;
            case 1:
                System.out.println("회원가입 성공");
                break;
            case 2:
                System.out.println("아이디 중복");
                break;
        }
    } // sign_up()

    public void logout(){ //로그아웃
        user = null;
    }// logout();

    public void write_board(){// 게시물 작성
        System.out.print("게시물 제목 >>> ");
        String title = scan.next();

        System.out.print("게시물 내용 >>> ");
        String contents = scan.next();

        int result = BoardController.getInstance().write_board(user.getUser_id(), title, contents);

        switch(result){
            case 0:
                System.out.println("게시물 제목 또는 게시물 내용 모두 입력해주세요");
                break;
            case 1:
                System.out.println("게시물 작성 성공");
                break;
        }
    }; // write_board()

    public void read_boardList(){ //게시물 목록 출력
        ArrayList<BoardDTO> boardList = BoardController.getInstance().read_boardList();
        
        for(BoardDTO boardDTO : boardList){
            UserDTO userDTO = UserController.getInstance().getUser(boardDTO.getUser_id());

            System.out.println(boardDTO.getBoard_id() + "번 게시물");
            System.out.println(" >> 게시물 작성자 : " + userDTO.getUserName());
            System.out.println(" >> 게시물 제목 : " + boardDTO.getTitle());
            System.out.println(" >> 게시물 작성일 : " + boardDTO.getWrite_date());
            System.out.println(" >> 게시물 조회수 : " + boardDTO.getView_count());
            System.out.println();

        }
    }//read_boardList()

    public void check_board(int user_id){ //게시물 조회
        System.out.print("조회할 게시물 번호 >> ");
        int board_id = scan.nextInt();

        BoardDTO boardDTO = BoardController.getInstance().check_board(board_id);

        if(boardDTO == null){
            System.out.println("해당 번호의 게시물이 없습니다");
            return;
        }

        ArrayList<CommentDTO> commentList = CommentController.getInstance().read_commentList(board_id);
        UserDTO userDTO = UserController.getInstance().getUser(boardDTO.getUser_id());

        System.out.println(boardDTO.getBoard_id() + "번 게시물");
        System.out.println(" >> 게시물 작성자 : " + userDTO.getUserName());
        System.out.println(" >> 게시물 제목 : " + boardDTO.getTitle());
        System.out.println(" >> 게시물 내용 : " + boardDTO.getContents());
        System.out.println(" >> 게시물 작성일 : " + boardDTO.getWrite_date());
        System.out.println(" >> 게시물 조회수 : " + boardDTO.getView_count());
        System.out.println(" >> 댓글");

        if(commentList.size() == 0){
            System.out.println("    >> 댓글 없음");
            return;
        }

        for(CommentDTO commentDTO : commentList){
            userDTO = UserController.getInstance().getUser(commentDTO.getUser_id());
            System.out.println("    >> " + userDTO.getUserName() + " : " + commentDTO.getComment() + "  " + commentDTO.getWrite_date());
        }
        System.out.println();

    }


    public void write_boardComment(int user_id){
        System.out.print("댓글 작성할 게시물 번호 >> ");
        int board_id = scan.nextInt();


        BoardDTO boardDTO = BoardController.getInstance().check_board(board_id);

        if(boardDTO == null){
            System.out.println("해당 번호의 게시물이 없습니다.");
            return;
        }

        System.out.print("댓글 >> ");
        String comment = scan.next();

        int result = CommentController.getInstance().write_comment(user_id, board_id, comment);

        if(result == 0){
            System.out.println("댓글 작성 실패!");
            return;
        }

        System.out.println("댓글 작성 성공");
    }
}
