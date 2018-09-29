package com.wanggoudan.www.service;

import com.wanggoudan.www.baseconfig.BasePage;
import com.wanggoudan.www.entity.UserEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Danny on 2018/8/21.
 */
public interface IUserService {
    UserEntity save(String username, String password,String fullname,Integer organizationId);

    Page<UserEntity> page(BasePage basePage);

    Page<UserEntity> pageByCondition(BasePage basePage,List<Integer> orgIds);
    UserEntity findOne(Integer id);

    UserEntity enabled(Integer userId, Integer roleId, boolean grant);

    UserEntity findByUsername(String username);

    List<UserEntity> findAll();
    void delete(Integer id);

    UserEntity update(Integer id, String username, String password, String fullname);

    List<UserEntity> findByOrgId(List<Integer> integers);

    UserEntity update(UserEntity userEntity);
}
