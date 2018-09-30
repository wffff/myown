package com.wanggoudan.www.controller;

import com.wanggoudan.www.baseconfig.util.SecurityUserUtils;
import com.wanggoudan.www.entity.UserEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("qq")
public class QQUserController {

    @RequestMapping("/user")
    public String user(@AuthenticationPrincipal UsernamePasswordAuthenticationToken userAuthentication, Model model) {
        UserEntity user = (UserEntity) userAuthentication.getPrincipal();
        model.addAttribute("main", "main");
        model.addAttribute("username", SecurityUserUtils.getSecurityUser().getFullname());
        return "index";
    }

}