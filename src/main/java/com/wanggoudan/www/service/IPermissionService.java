package com.wanggoudan.www.service;


import com.wanggoudan.www.baseconfig.BasePage;
import com.wanggoudan.www.entity.PermissionEntity;
import org.springframework.data.domain.Page;

import java.util.List;


public interface IPermissionService {
    Page<PermissionEntity> page(BasePage basePage);

    PermissionEntity save(String name, String description);

    PermissionEntity findOne(Integer id);

    List<PermissionEntity> findAll();

    void delete(Integer id);

    PermissionEntity edit(Integer id, String name, String description);
}
