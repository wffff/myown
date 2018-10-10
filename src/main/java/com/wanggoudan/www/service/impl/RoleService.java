package com.wanggoudan.www.service.impl;

import com.wanggoudan.www.baseconfig.BasePage;
import com.wanggoudan.www.baseconfig.IBaseNativeSqlRepository;
import com.wanggoudan.www.baseconfig.util.SecurityUserUtils;
import com.wanggoudan.www.entity.PermissionEntity;
import com.wanggoudan.www.entity.RoleEntity;
import com.wanggoudan.www.entity.UserEntity;
import com.wanggoudan.www.repository.IRoleRepository;
import com.wanggoudan.www.service.IPermissionService;
import com.wanggoudan.www.service.IRoleService;
import com.wanggoudan.www.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class RoleService implements IRoleService {
    @Resource
    private IRoleRepository iRoleRepository;

    @Resource
    private IPermissionService iPermissionService;
    @Resource
    private IUserService iUserService;

    public RoleEntity save(String name, String description) {
        RoleEntity r = new RoleEntity();
        r.setName(name);
        r.setDescription(description);
        r.setTime(new Date());
        RoleEntity save = iRoleRepository.save(r);
        return save;
    }

    public Page<RoleEntity> page(BasePage basePage) {
        return iRoleRepository.findAllByDelFalse(basePage.getRequestPage());
    }

    @Override
    public RoleEntity enabled(Integer roleId, Integer permissionId, boolean enabled) {
        RoleEntity r = iRoleRepository.findById(roleId).get();
        PermissionEntity p = iPermissionService.findOne(permissionId);

        if (enabled) {
            r.getPermission().add(p);
        } else {
            r.getPermission().remove(p);
        }
        return iRoleRepository.save(r);
    }

    @Override
    public RoleEntity findOne(Integer id) {
        return iRoleRepository.findById(id).get();
    }

    @Override
    public List<RoleEntity> findAll() {
        return iRoleRepository.findAllByDelFalse();
    }

    @Override
    public void delete(List<Integer> id) {
        iRoleRepository.removeAll(id);
    }

    @Override
    public RoleEntity edit(Integer id, String name, String description) {
        RoleEntity r;
        if (id == null) {
            r = new RoleEntity();
        } else {
            r = iRoleRepository.findOne(id);
        }
        r.setName(name);
        r.setDescription(description);
        return iRoleRepository.save(r);
    }


}
