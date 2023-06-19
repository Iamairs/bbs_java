package com.ncu.bbs.service.Impl;

import com.ncu.bbs.dao.UserLoginLogDao;
import com.ncu.bbs.pojo.UserLoginLog;
import com.ncu.bbs.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    UserLoginLogDao userLoginLogDao;

    @Override
    public void listAllUserLoginLog() {

    }

    @Override
    public void addUserLoginLog(UserLoginLog userLoginLog) {
        userLoginLogDao.addUserLoginLog(userLoginLog);
    }
}
