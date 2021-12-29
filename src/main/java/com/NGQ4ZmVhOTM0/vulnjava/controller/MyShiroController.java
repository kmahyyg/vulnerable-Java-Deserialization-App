package com.NGQ4ZmVhOTM0.vulnjava.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyShiroController {
    @ResponseBody
    @RequestMapping("/shiro/authSucc")
    public String shiroShowAuthedData(HttpServletRequest req) {
        req.getSession(true);
        return "your are an authenticated user";
    }

    @ResponseBody
    @RequestMapping("/shiro/anonFail")
    public String shiroShowFailed(HttpServletRequest req) {
        req.getSession(true);
        return "failed to authenticate";
    }

    @ResponseBody
    @RequestMapping("/shiro/anonShow")
    public String shiroShowAnonData() {
        return "your are an anonymous user. To login: /shiro/anonLogin";
    }

    @RequestMapping("/shiro/anonLogin")
    public String shiroShowLogin(){
        return "login";
    }

    @PostMapping("/shiro/anonDoLogin")
    public String doLoginPage(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam(name="rememberme", defaultValue = "") String rememberMe) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password, rememberMe.equals("remember-me")));
        } catch (AuthenticationException e) {
            return "forward:/shiro/anonFail";
        }
        return "redirect:/shiro/authSucc";
    }
}
