package com.wanggoudan.www.controller;

import com.wanggoudan.www.baseconfig.util.RegexUtils;
import com.wanggoudan.www.baseconfig.util.SecurityUserUtils;
import com.wanggoudan.www.service.IUploadService;
import com.wanggoudan.www.service.IUserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.Principal;
import java.util.Map;
import java.util.Set;

/**
 * Created by Danny on 2018/8/21.
 */
@Controller
public class BaseController {
    @Resource
    private IUploadService iUploadService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/principal")
    @ResponseBody
    public Principal principal(Principal principal) {
        return principal;
    }

    @RequestMapping("/admin")
    @ResponseBody
    @Secured(value = "ROLE_ADMIN")
    public String admin(Principal principal) {
        return principal.getName();
    }

    @RequestMapping("/uploadImg")//用于ckeditor中上传图片？
    @ResponseBody
    public Map uploadImg(@RequestParam("upload") MultipartFile file,
                         HttpServletResponse response,
                         HttpServletRequest request)
            throws IllegalStateException, IOException {
        Map m = iUploadService.uploadImg(file);
        return m;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public Map upload(MultipartFile file,
                      HttpServletResponse response,
                      HttpServletRequest request)
            throws IllegalStateException, IOException {
        Map m = iUploadService.uploadImg(file);
        return m;
    }

}
