package com.ncu.bbs.interceptor;

import com.ncu.bbs.util.TokenUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Token拦截器
 * @author : XYS
 * @date : 2023-05-29 9:27
 */
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        String requestMethod = request.getMethod();
        if(!Objects.equals(requestMethod, "GET") && (!url.endsWith("user/userLogin") && !url.endsWith("user/register"))){
            //从http请求头中取出token
            String token = request.getHeader("Authorization");
            //执行认证
            if(TokenUtil.verify(token)){
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}

