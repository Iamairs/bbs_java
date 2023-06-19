package com.ncu.bbs.controller;

import com.ncu.bbs.pojo.TokenResponse;
import com.ncu.bbs.pojo.User;
import com.ncu.bbs.pojo.UserLoginLog;
import com.ncu.bbs.service.LoginLogService;
import com.ncu.bbs.service.UserService;
import com.ncu.bbs.util.Result;
import com.ncu.bbs.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private LoginLogService loginLogService;

    // 用户登录
    @PostMapping("/userLogin")
    public Result<TokenResponse> userLogin(@RequestBody User loginUser) {
        // 通过用户名查找User对象
        User user = userService.getUserByUserName(loginUser.getUserName());
        String password = "";
        if (user != null) {
            password = userService.getPassword(user.getUserName());
        } else {
            return Result.buildResult(Result.Status.UNAUTHORIZED);
        }

        // 判断登录信息是否正确
        if (user != null && loginUser.getPassword().equals(password)) {
            // 获取登录基本信息
            String userName = user.getUserName();
            Timestamp lastLoginTime = new Timestamp(new Date().getTime());

            // 更新用户信息
            user.setLastLoginTime(lastLoginTime);
            user.setCredit(5 + user.getCredit());
            userService.updateUserByUserName(user);

            // 更新用户登录日志
            UserLoginLog userLoginLog = new UserLoginLog();
            userLoginLog.setUserName(userName);
            userLoginLog.setLoginDateTime(lastLoginTime);
            loginLogService.addUserLoginLog(userLoginLog);

            // 生成token
            String token = TokenUtil.sign(loginUser.getUserName());
            // 创建TokenResponse对象并返回
            TokenResponse tokenResponse = new TokenResponse(user, token);
            return Result.buildResult(Result.Status.OK, tokenResponse);
        } else {
            // 密码错误
            return Result.buildResult(Result.Status.PWD_ERROR);
        }
    }

    // 用户注册
    @PostMapping("/register")
    public Result<TokenResponse> userRegister(@RequestBody User userRegister) {
        User user = userRegister;
        if (user != null) {
            try {
                String username = user.getUserName();

                // 如果数据库中没有该用户，可以注册，否则跳转页面
                if (userService.getUserByUserName(username) == null) {

                    // 添加用户
                    Timestamp createLoginTime = new Timestamp(new Date().getTime());
                    user.setCreateTime(createLoginTime);
                    user.setLastLoginTime(createLoginTime);
                    user.setUserType(1);
                    userService.addUser(user);

                    // 生成token
                    String token = TokenUtil.sign(userRegister.getUserName());
                    // 创建TokenResponse对象并返回
                    TokenResponse tokenResponse = new TokenResponse(user, token);
                    // 注册成功跳转
                    return Result.buildResult(Result.Status.OK,tokenResponse);
                } else {
                    return Result.buildResult(Result.Status.EXIT);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Result.buildResult(Result.Status.INTERNAL_SERVER_ERROR);
            }
        }
        return Result.buildResult(Result.Status.INTERNAL_SERVER_ERROR);
    }

    // 显示个人信息
    @GetMapping(value = "/listUserInfo")
    public Result<User> listUserInfo(@RequestParam("userName") String userName) {
        System.out.println("userName: " + userName);
        User newUser = userService.getUserByUserName(userName);
        if(newUser!=null){
            return Result.buildResult(Result.Status.OK,newUser);
        }
        return Result.buildResult(Result.Status.UNAUTHORIZED);
    }

    // 修改个人信息页面
    @PutMapping(value = "/userUpdateInfo")
    public Result<User> userUpdateInfoPage(@RequestBody User user) {
        userService.updateUserByUserName(user);
        User newUser=userService.getUserByUserName(user.getUserName());
        return Result.buildResult(Result.Status.OK,newUser);
    }
}
