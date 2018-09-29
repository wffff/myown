package com.snkj.www.controller;

import com.snkj.www.baseconfig.ReturnMessage;
import com.snkj.www.entity.PermissionEntity;
import com.snkj.www.entity.RoleEntity;
import com.snkj.www.service.IPermissionService;
import com.snkj.www.service.IRoleService;
import org.hibernate.annotations.Parameter;
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
    @RequestMapping("page")
    @ResponseBody
    public ReturnMessage<List<PermissionEntity>> page(@RequestParam(value = "page",defaultValue = "1")Integer page,@RequestParam(value = "limit",defaultValue = "20")Integer limit){
        Page<PermissionEntity> p =iPermissionService.page(page,limit);
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
    public ReturnMessage<List<PermissionEntity>> findAll(Integer id){
        RoleEntity one = iRoleService.findOne(id);
        Set<PermissionEntity> permissionHas = one.getPermission();
        List<PermissionEntity> all = iPermissionService.findAll();
        for (PermissionEntity p:all){
            if (permissionHas.contains(p)){
                p.setEnabled(true);
            }
        }
        return ReturnMessage.success(all.size(),all);
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

    @RequestMapping("edit")
    @ResponseBody
    public ReturnMessage<PermissionEntity> edit(Integer id, String name, String description){
        PermissionEntity p = iPermissionService.edit(id,name,description);
        if(p!=null){
            return ReturnMessage.success("权限编辑成功",p);
        }else {
            return ReturnMessage.failed("编辑失败");
        }
    }
}
