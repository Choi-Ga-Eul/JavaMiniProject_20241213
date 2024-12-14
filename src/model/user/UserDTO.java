package model.user;

public class UserDTO {
    private int user_id;
    private String userName;
    private String id;
    private String password;

    public UserDTO(int user_id, String userName, String id, String password){
        this.user_id = user_id;
        this.userName = userName;
        this.id = id;
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "user_id='" + user_id + '\'' +
                ", username='" + userName + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
