package com.wanggoudan.www.controller;

import com.wanggoudan.www.baseconfig.ReturnMessage;
import com.wanggoudan.www.entity.OrganizationEntity;
import com.wanggoudan.www.enums.OrganizationTypeEnum;
import com.wanggoudan.www.service.IOrganizationService;
import com.wanggoudan.www.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Danny on 2018/7/13.
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController {
    @Resource
    private IOrganizationService iOrganizationService;

    @RequestMapping("main")
    public String main(){
        return "organization/main";
    }
    @RequestMapping("/list")
    @ResponseBody
    public List<OrganizationEntity> list() {
        List<OrganizationEntity> all = iOrganizationService.findAll();
        return all;
    }

    @RequestMapping("/add")
    @ResponseBody
    public ReturnMessage<OrganizationEntity> add(Integer pid, OrganizationTypeEnum organizationType, String name, String deviceName) {
        OrganizationEntity org = iOrganizationService.save(pid, organizationType, name, deviceName);
        return ReturnMessage.success("创建成功" + org.getName());
    }

    @RequestMapping("/remove")
    @ResponseBody
    public ReturnMessage<OrganizationEntity> remove(Integer id) {
        iOrganizationService.remove(id);
        return ReturnMessage.success("删除" + id + "成功");
    }

    @RequestMapping("/update")
    @ResponseBody
    public ReturnMessage<OrganizationEntity> update(Integer id, String name, String deviceName) {
        iOrganizationService.update(id, name, deviceName);
        return ReturnMessage.success("更新" + id + "成功");
    }


    @RequestMapping("/test")
    @ResponseBody
    public List<Integer> test(Integer id, boolean up, boolean self, boolean recursive) {
        return iOrganizationService.listIdsByConditions(id, up, self, recursive);
    }


    @RequestMapping("/test2")
    @ResponseBody
    public List<OrganizationEntity> test2(Integer id) {

        List<Integer> integers = iOrganizationService.listIdsByConditions(id, false, true, true);
        return iOrganizationService.listDevice(integers);
    }
}
