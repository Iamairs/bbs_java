package com.ncu.bbs.service;

import com.ncu.bbs.pojo.User;

import java.util.List;

public interface UserService {
    public void addUser(User user);

    public void updateUserByUserName(User user);

    public User getUserByUserName(String userName);

    public void deleteUserByUserName(String userName);

    public void loginSuccess(User user);

    public String getPassword(String userName);

    public List<User> getAllUser();
}
