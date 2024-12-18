package controller.user;

import model.user.UserDAO;
import model.user.UserDTO;

public class UserController {
    private static UserController userController;

    public static UserController getInstance(){
        if(userController == null){
            userController = new UserController();
        }

        return userController;
    }

    public UserDTO sign_in(String id, String password){
        UserDTO userDTO = UserDAO.getInstance().getUser(id, password);

        return userDTO;
    }

    public int sign_up(String id, String password, String userName){
        boolean idCheck = UserDAO.getInstance().idCheck(id);

        if(idCheck){//아이디 중복
            return 2;
        }

        int result = UserDAO.getInstance().insertUser(id, password, userName);

        return result;
    }

    public UserDTO getUser(int user_id){
        return UserDAO.getInstance().getUser(user_id);
    }
}
