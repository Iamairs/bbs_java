package com.ncu.bbs.controller.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleCORSFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // 允许所有来源进行跨域请求
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        // 允许特定的请求方法
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        // 允许包含特定请求头的请求
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        // 允许发送跨域请求时携带凭证（如 Cookie）
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");

        chain.doFilter(request, response);
    }
}

