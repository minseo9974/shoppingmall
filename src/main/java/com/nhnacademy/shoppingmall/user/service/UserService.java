package com.nhnacademy.shoppingmall.user.service;

import com.nhnacademy.shoppingmall.user.domain.User;
import java.util.List;

public interface UserService {

    User getUser(String userId);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(String userId);

    User doLogin(String userId, String userPassword);

    int getUserCount();

    int getAdminCount();

    List<User> getAdminPageList(int offset,int pageSize);
    List<User> getUserPageList(int offset,int pageSize);


}
