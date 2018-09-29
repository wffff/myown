package com.snkj.www.service.impl;

import com.snkj.www.baseconfig.IBaseNativeSqlRepository;
import com.snkj.www.baseconfig.util.SecurityUserUtils;
import com.snkj.www.entity.PermissionEntity;
import com.snkj.www.entity.RoleEntity;
import com.snkj.www.entity.UserEntity;
import com.snkj.www.repository.IRoleRepository;
import com.snkj.www.service.IOrganizationService;
import com.snkj.www.service.IPermissionService;
import com.snkj.www.service.IRoleService;
import com.snkj.www.service.IUserService;
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
    private IBaseNativeSqlRepository iBaseNativeSqlRepository;
    @Resource
    private IOrganizationService iOrganizationService;

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
        UserEntity userEntity=iUserService.findOne(SecurityUserUtils.getUserId());
        userEntity.getRole().add(save);
        iUserService.update(userEntity);
        return save;
    }

    public Page<RoleEntity> page(Integer page, Integer limit) {
        return iRoleRepository.findAllByDelFalse(PageRequest.of(page - 1, limit));
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
    public void delete(Integer id) {
        RoleEntity r = iRoleRepository.findOne(id);
        Set<PermissionEntity> permissions = r.getPermission();
        List<PermissionEntity> all = iPermissionService.findAll();
        List<UserEntity> users = iUserService.findAll();
        for (PermissionEntity p : all) {
            if (permissions.contains(p)) {
                r.getPermission().remove(p);
            }
        }
        for (UserEntity u : users) {
            u.getRole().remove(r);
        }
        r.setDel(true);
        iRoleRepository.save(r);
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

    @Override
    public List<RoleEntity> findByUser() {
        List<Integer> integers = iOrganizationService.listOrgTree(SecurityUserUtils.getOrganization().getId());
        List<UserEntity> allUsers = iUserService.findByOrgId(integers);
        List<Integer> list1 = new ArrayList<>();
        for (UserEntity user : allUsers) {
            list1.add(user.getId());
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : list1) {
            sb.append(i + ",");
        }
        String sql = String.format("select ur.role_id from t_user_role ur where ur.user_id in (%s)", sb.substring(0, sb.length() - 1));
        List list = iBaseNativeSqlRepository.listByNativeSql(sql);
        Set<Integer> set = new HashSet<>(list);
        List<RoleEntity> all = iRoleRepository.findAll(set);
        return all;
    }

}
