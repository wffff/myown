package com.wanggoudan.www.controller;

import com.wanggoudan.www.baseconfig.BasePage;
import com.wanggoudan.www.baseconfig.ReturnMessage;
import com.wanggoudan.www.entity.PermissionEntity;
import com.wanggoudan.www.entity.RoleEntity;
import com.wanggoudan.www.service.IPermissionService;
import com.wanggoudan.www.service.IRoleService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("permission")
public class PermissionController {
    @Resource
    private IPermissionService iPermissionService;
    @Resource
    private IRoleService iRoleService;
    @RequestMapping("permission")
    public String permission(){
        return "user/permission";
    }
    @RequestMapping("page")
    @ResponseBody
    public ReturnMessage<List<PermissionEntity>> page(BasePage basePage){
        Page<PermissionEntity> p =iPermissionService.page(basePage);
        return ReturnMessage.success((int) p.getTotalElements(), p.getContent());
    }

    @RequestMapping("save")
    @ResponseBody
    public ReturnMessage<PermissionEntity> save(String name, String description){
        PermissionEntity p = iPermissionService.save(name,description);
        return ReturnMessage.success(0,p);
    }

    @RequestMapping("findAll")
    @ResponseBody
    public List<PermissionEntity> findAll(Integer id){
        RoleEntity one = iRoleService.findOne(id);
        Set<PermissionEntity> permissionHas = one.getPermission();
        List<PermissionEntity> all = iPermissionService.findAll();
        for (PermissionEntity p:all){
            if (permissionHas.contains(p)){
                p.setEnabled(true);
            }
        }
        return all;
    }

    @RequestMapping("grant")
    @ResponseBody
    public ReturnMessage grant(Integer roleId,Integer permissionId,boolean grant){
        try {
            RoleEntity enabled = iRoleService.enabled(roleId, permissionId, grant);//??
            return ReturnMessage.success(1,enabled);
        }catch (Exception e){
            return ReturnMessage.failed();
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public ReturnMessage delete(@RequestParam("id") List<Integer> ids){
        Iterator it = ids.iterator();
        while(it.hasNext()){
            Integer id = (Integer) it.next();
            iPermissionService.delete(id);
        }
        return ReturnMessage.success();
    }

    @RequestMapping("update")
    @ResponseBody
    public ReturnMessage<PermissionEntity> update(Integer id, String name, String description){
        PermissionEntity p = iPermissionService.edit(id,name,description);
        if(p!=null){
            return ReturnMessage.success("权限编辑成功",p);
        }else {
            return ReturnMessage.failed("编辑失败");
        }
    }

    @RequestMapping("detail")
    @ResponseBody
    public ReturnMessage<PermissionEntity> detail(Integer id) {
        PermissionEntity p = iPermissionService.findOne(id);
        if (p != null) {
            return ReturnMessage.success(1,p,"查询成功");
        } else {
            return ReturnMessage.failed("查询权限失败");
        }
    }
}
