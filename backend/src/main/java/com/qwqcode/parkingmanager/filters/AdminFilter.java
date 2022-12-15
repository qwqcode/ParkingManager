package com.qwqcode.parkingmanager.filters;

import com.qwqcode.parkingmanager.config.CorsConfig;
import com.qwqcode.parkingmanager.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebFilter(filterName = "adminFilter", urlPatterns = {"/api/admin/*"})
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 从 session 中获取登录信息
        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute("user");

        // 校验登录信息 + 用户身份
        if (user != null && user.getRole() == User.ROLE_ADMIN) {
            // 登录信息正确，继续执行请求
            chain.doFilter(request, response);
        } else {
            // 登录信息不正确，返回 JSON 格式的错误信息
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setHeader("Content-Type", "application/json");
            httpResponse.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5173");
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setCharacterEncoding("UTF-8");
            PrintWriter writer = httpResponse.getWriter();
            writer.println("{");
            writer.println("\"success\": false,");
            writer.println("\"msg\": \"无权访问\"");
            writer.println("}");
        }
    }

    @Override
    public void destroy() {

    }
}