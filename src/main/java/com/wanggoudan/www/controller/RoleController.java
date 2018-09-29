package com.wanggoudan.www.controller;

import com.wanggoudan.www.baseconfig.ReturnMessage;
import com.wanggoudan.www.baseconfig.util.SecurityUserUtils;
import com.wanggoudan.www.entity.RoleEntity;
import com.wanggoudan.www.entity.UserEntity;
import com.wanggoudan.www.service.IRoleService;
import com.wanggoudan.www.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("role")
public class RoleController {
    @Resource
    private IRoleService iRoleService;
    @Resource
    private IUserService iUserService;


    @RequestMapping("save")
    @ResponseBody
    public ReturnMessage<RoleEntity> save(String name, String description) {
        RoleEntity r = iRoleService.save(name, description);
        return ReturnMessage.success(0, r);
    }

    @RequestMapping("list")
    @ResponseBody
    public ReturnMessage<List<RoleEntity>> list() {
        List<RoleEntity> r;
        if (SecurityUserUtils.isAdmin()) {
            r = iRoleService.findAll();
        } else {
           return ReturnMessage.success();
        }
        return ReturnMessage.success(r.size(), r);
    }

    @RequestMapping("findAll")
    @ResponseBody
    public ReturnMessage<List<RoleEntity>> findAll(Integer id) {//当前id是用户id
        UserEntity one = iUserService.findOne(id);
        Set<RoleEntity> roles = one.getRole();
        List<RoleEntity> all = iRoleService.findAll();
        for (RoleEntity r : all) {
            if (roles.contains(r)) {
                r.setEnabled(true);
            }
        }
        return ReturnMessage.success(all.size(), all);
    }

    @RequestMapping("grant")
    @ResponseBody
    public ReturnMessage grant(Integer userId, Integer roleId, boolean grant) {
        try {
            UserEntity enabled = iUserService.enabled(userId, roleId, grant);//??
            return ReturnMessage.success(1, enabled);
        } catch (Exception e) {
            return ReturnMessage.failed();
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public ReturnMessage delete(@RequestParam("id") List<Integer> ids) {
        Iterator it = ids.iterator();
        while (it.hasNext()) {
            Integer id = (Integer) it.next();
            iRoleService.delete(id);
        }
        return ReturnMessage.success();
    }

    @RequestMapping("edit")
    @ResponseBody
    public ReturnMessage<RoleEntity> edit(Integer id, String name, String description) {
        RoleEntity r = iRoleService.edit(id, name, description);
        if (r != null) {
            return ReturnMessage.success("角色编辑成功", r);
        } else {
            return ReturnMessage.failed("编辑失败");
        }
    }

}
