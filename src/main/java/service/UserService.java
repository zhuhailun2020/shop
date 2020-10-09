package service;

import entity.User;

public interface UserService {
    boolean registUser(User user);

    User login(String email, String password) throws Exception;

    boolean checkUser(String uname);

    boolean active(String code);

    boolean checkEmail(String email);
}
