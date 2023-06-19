package com.ncu.bbs.dao;

import com.ncu.bbs.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    public User findUserByUserName(String username);

    public User findUserByUserId(int id);

    public void addUser(User user);

    public void deleteUserByUserName(String username);

    public void updateUserByUserName(User user);

    public String getUserPasswordByUserName(String username);

    public List<User> getAllUserInfo();
}
