package dao;

import entity.User;

public interface UserDao {
    void registUser(User user);

    User findUserByEmailAndPassword(String email, String password);

    User checkUser(String uname);

    User checkEmail(String email);

    int updateUserStatus(String code);

}
