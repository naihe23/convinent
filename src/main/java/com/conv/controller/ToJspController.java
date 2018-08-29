package com.conv.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller

@RequestMapping("/user")
public class ToJspController {

    @RequestMapping(value = "/register")
    public String registerUser() {
        return "register";
    }

    @RequestMapping("/login")
    public String loginDo() {
        return "user_login";
    }

    @RequestMapping("/forget")
    public String forget(){
        return "edit_password";
    }

    @RequestMapping(value = "/index.do")
    public String indexDo(){
        return "index";
    }

    @RequestMapping("/edit")
    public String forgetPassword(HttpServletRequest request, Model model){
        int userId = Integer.parseInt(request.getParameter("userId"));
        model.addAttribute("userId",userId);
        return "edit";
    }
}
