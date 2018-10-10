package com.wanggoudan.www.service;

import com.wanggoudan.www.baseconfig.BasePage;
import com.wanggoudan.www.entity.RoleEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRoleService {
    RoleEntity save(String name, String description);

    Page<RoleEntity> page(BasePage basePage);

    RoleEntity enabled(Integer roleId, Integer permissionId, boolean enabled);//boolean enabled就是从PermissionController传过来的grant

    RoleEntity findOne(Integer id);

    List<RoleEntity> findAll();

    void delete(List<Integer> id);

    RoleEntity edit(Integer id, String name, String description);

}
