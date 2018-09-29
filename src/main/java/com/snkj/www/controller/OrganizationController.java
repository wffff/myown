package com.snkj.www.controller;

import com.snkj.www.baseconfig.ReturnMessage;
import com.snkj.www.baseconfig.util.SecurityUserUtils;
import com.snkj.www.entity.OrganizationEntity;
import com.snkj.www.enums.OrganizationTypeEnum;
import com.snkj.www.service.IOrganizationService;
import com.snkj.www.service.IUserService;
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
    @Resource
    private IUserService iUserService;

    @RequestMapping("/list")
    @ResponseBody
    public ReturnMessage<List<OrganizationEntity>> list() {
        Integer organizationId = SecurityUserUtils.getSecurityUser().getOrganizationId();
        if (SecurityUserUtils.isAdmin()) {
            List<OrganizationEntity> all = iOrganizationService.findAll();
            return ReturnMessage.success(all.size(), all);
        }else {
            List<OrganizationEntity> org = iOrganizationService.listOrgByConditions(organizationId, false, true, true);
            for (OrganizationEntity organization : org) {
                if (SecurityUserUtils.getOrganization().getId().equals(organization.getId())) {
                    organization.setParentId(null);
                }
            }
            return ReturnMessage.success(org.size(), org);
        }
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

    @RequestMapping("/getMyOrganization")
    @ResponseBody
    public ReturnMessage<List<OrganizationEntity>> getMyOrganization() {
        boolean admin = SecurityUserUtils.isAdmin();
        if (admin) {
            List<OrganizationEntity> all = iOrganizationService.findAll();
            return ReturnMessage.success(all.size(), all);
        } else {
            List<OrganizationEntity> organizationEntities = iOrganizationService.listOrgByConditions(SecurityUserUtils.getOrganization().getId(), false, true, true);
            for (OrganizationEntity org : organizationEntities) {
                if (SecurityUserUtils.getOrganization().getId().equals(org)) {
                    org.setChecked(true);
                }
            }
            return ReturnMessage.success(organizationEntities.size(), organizationEntities);
        }

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
