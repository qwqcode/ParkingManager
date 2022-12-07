package com.qwqcode.parkingmanager.controller;

import com.qwqcode.parkingmanager.entity.User;
import com.qwqcode.parkingmanager.model.req.*;
import com.qwqcode.parkingmanager.model.res.CommonResp;
import com.qwqcode.parkingmanager.service.UserService;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/api/login")
    public CommonResp userLogin(UserLoginParams params, HttpServletRequest req) {
        User user = userService.checkGetUser(params.getPhone(), params.getPassword());

        if (user == null) {
            return CommonResp.Error("登录失败");
        }

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("user", user);

        return CommonResp.Success("登录成功");
    }

    @PostMapping("/api/signup")
    public CommonResp userSignup(UserSignupParams params, HttpServletRequest req) {
        boolean result = userService.signupUser(params.getPhone(), params.getPassword());
        if (result) return CommonResp.Success("注册成功");
        else return CommonResp.Error("注册失败");
    }

    @PostMapping("/api/user/me")
    public CommonResp userInfo(HttpServletRequest req) {
        HttpSession session = req.getSession();
        User user = userService.getUserByID(((User)session.getAttribute("user")).getId());

        return CommonResp.Data(user);
    }
}
