package com.ncu.bbs.dao;

import com.ncu.bbs.pojo.UserLoginLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLoginLogDao {
    public List<UserLoginLog> listAllUserLoginLog();

    public void addUserLoginLog(UserLoginLog userLoginLog);
}
