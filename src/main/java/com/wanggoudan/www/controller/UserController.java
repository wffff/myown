package com.wanggoudan.www.controller;


import com.wanggoudan.www.baseconfig.BasePage;
import com.wanggoudan.www.baseconfig.ReturnMessage;
import com.wanggoudan.www.entity.UserEntity;
import com.wanggoudan.www.service.IUserService;
import com.wanggoudan.www.service.impl.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("user")
//@Secured(value = "ROLE_USER")
public class UserController {
    @Resource
    private IUserService iUserService;
    @Resource
    private UserService userService;

    @RequestMapping("getInfo")
    @ResponseBody
    public UserEntity getInfo(String username,String fullname){
        UserEntity byUsername = iUserService.findByUsername(username);
        if (byUsername==null){
            UserEntity userEntity=new UserEntity();
            userEntity.setUsername(username);
            userEntity.setFullname(fullname);
            byUsername= iUserService.save(userEntity);
        }
        byUsername=userService.loadUserByUsername(byUsername.getUsername());
        return byUsername;
    }
    @RequestMapping("save")
    @ResponseBody
    public ReturnMessage<UserEntity> save(String username, String password, String fullname, Integer organizationId) {
        UserEntity u = iUserService.save(username, password, fullname, organizationId);
        return ReturnMessage.success(0, u);
    }

    @RequestMapping("saveNew")
    @ResponseBody
    @Secured("ROLE_USER_ADD_NEW")
    public ReturnMessage<UserEntity> saveNew(String username, String password, String fullname, Integer organizationId) {
        return save(username, password, fullname, organizationId);
    }

    @RequestMapping("page")
    @ResponseBody
    public ReturnMessage<List<UserEntity>> page(BasePage basePage) {
        Page<UserEntity> p = iUserService.page(basePage);
        return ReturnMessage.success((int) p.getTotalElements(), p.getContent());
    }

    @RequestMapping("check")
    @ResponseBody
    public ReturnMessage<UserEntity> check(String username) {
        UserEntity u = iUserService.findByUsername(username);
        return ReturnMessage.success(1, u);
    }

    @RequestMapping("delete")
    @ResponseBody
    public ReturnMessage delete(@RequestParam("id") List<Integer> ids) {
        Iterator it = ids.iterator();
        while (it.hasNext()) {
            Integer id = (Integer) it.next();

            iUserService.delete(id);
        }
        return ReturnMessage.success();
    }

    @RequestMapping("update")
    @ResponseBody
    public ReturnMessage<UserEntity> update(Integer id, String username, String password, String fullname) {
        UserEntity u = iUserService.update(id, username, password, fullname);
        if (u != null) {
            return ReturnMessage.success("用户信息编辑成功", u);
        } else {
            return ReturnMessage.failed("修改失败");
        }
    }
    @RequestMapping("detail")
    @ResponseBody
    public ReturnMessage<UserEntity> detail(Integer id) {
        UserEntity u = iUserService.findOne(id);
        if (u != null) {
            return ReturnMessage.success(1,u,"查询成功");
        } else {
            return ReturnMessage.failed("查询用户失败");
        }
    }


}
