package com.ncu.bbs.service;

import com.ncu.bbs.pojo.UserLoginLog;

public interface LoginLogService {
    public void listAllUserLoginLog();

    public void addUserLoginLog(UserLoginLog userLoginLog);
}
