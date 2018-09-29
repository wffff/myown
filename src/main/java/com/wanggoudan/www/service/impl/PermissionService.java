package com.wanggoudan.www.service.impl;

import com.wanggoudan.www.entity.PermissionEntity;
import com.wanggoudan.www.entity.RoleEntity;
import com.wanggoudan.www.repository.IPermissionRepository;
import com.wanggoudan.www.service.IPermissionService;
import com.wanggoudan.www.service.IRoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class PermissionService implements IPermissionService {
    @Resource
    private IPermissionRepository iPermissionRepository;
    @Resource
    private IRoleService iRoleService;

    public Page<PermissionEntity> page(Integer page, Integer limit) {
        return iPermissionRepository.findAllByDelFalse(PageRequest.of(page - 1, limit));
    }

    public PermissionEntity save(String name, String description) {
        PermissionEntity p = new PermissionEntity();
        p.setName(name);
        p.setDescription(description);
        p.setTime(new Date());
        return iPermissionRepository.save(p);
    }

    @Override
    public PermissionEntity findOne(Integer id) {
        return iPermissionRepository.findById(id).get();
    }

    @Override
    public List<PermissionEntity> findAll() {
        return iPermissionRepository.findAllByDelFalse();
    }

    @Override
    public void delete(Integer id) {
        PermissionEntity p = iPermissionRepository.findOne(id);
        List<RoleEntity> roles = iRoleService.findAll();
        for (RoleEntity r : roles) {
            r.getPermission().remove(p);
        }
        p.setDel(true);
        iPermissionRepository.save(p);
    }

    @Override
    public PermissionEntity edit(Integer id, String name, String description) {
        PermissionEntity p;
        if (id == null) {
            p = new PermissionEntity();
        } else {
            p = iPermissionRepository.findOne(id);
        }
        p.setName(name);
        p.setDescription(description);
        return iPermissionRepository.save(p);
    }

}
