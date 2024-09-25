package com.ProFit.controller.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        // 判断 session 中是否有 "user_email" 的 key
        HttpSession session = request.getSession();

        if (session.getAttribute("user_email") == null) {
            response.sendRedirect("loginpage");
            return false; // 返回 false 阻止請求繼續
        }
        return true; // 返回 true 繼續請求
    }
}
