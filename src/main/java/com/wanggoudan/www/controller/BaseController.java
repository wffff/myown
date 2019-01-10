package com.wanggoudan.www.controller;

import com.qcloud.Utilities.Json.JSONObject;
import com.wanggoudan.www.baseconfig.util.HttpUtils;
import com.wanggoudan.www.baseconfig.util.RegexUtils;
import com.wanggoudan.www.entity.UserEntity;
import com.wanggoudan.www.service.IUploadService;
import com.wanggoudan.www.service.IUserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Danny on 2018/8/21.
 */
@Controller
public class BaseController {
    @Resource
    private IUploadService iUploadService;
    @Resource
    private IUserService iUserService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/openId")
    @ResponseBody
    public Map<String, Object> openId(String code) { // 小程序端获取的CODE
        Map<String, Object> result = new HashMap<>();
        try {
            boolean check = (!RegexUtils.notNull(code)) ? true : false;
            if (check) {
                throw new Exception("参数异常");
            }
            StringBuilder urlPath = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session"); // 微信提供的API，这里最好也放在配置文件
            Map<String, String> parameters = new HashMap<>();
            parameters.put("appid", "wxfbd2a5415cd145da");
            parameters.put("secret", "690b406275638bdf037d4ad7ab5dc9e5");
            parameters.put("js_code", code);
            parameters.put("grant_type", "authorization_code");
            String data = HttpUtils.sendPost(urlPath.toString(), parameters); // java的网络请求，这里是我自己封装的一个工具包，返回字符串
            System.out.println("请求结果：" + data);
            String openId = new JSONObject(data).getString("openid");
            System.out.println("获得openId: " + openId);
            UserEntity byOpenId = iUserService.findByOpenId(openId);
            if (byOpenId != null) {
                result.put("code", 0);
                result.put("userId", byOpenId.getId());
                result.put("user", byOpenId);
            } else {
                result.put("code", -1);
            }
        } catch (Exception e) {
            result.put("code", 1);
            result.put("remark", e.getMessage());
            e.printStackTrace();
        }
        return result;
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
