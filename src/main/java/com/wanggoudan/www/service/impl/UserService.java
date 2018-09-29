package com.wanggoudan.www.service.impl;

import com.wanggoudan.www.baseconfig.BasePage;
import com.wanggoudan.www.entity.PermissionEntity;
import com.wanggoudan.www.entity.RoleEntity;
import com.wanggoudan.www.entity.UserEntity;
import com.wanggoudan.www.repository.IUserRepository;
import com.wanggoudan.www.service.IRoleService;
import com.wanggoudan.www.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Danny on 2018/8/21.
 */
@Service
public class UserService implements IUserService, UserDetailsService {
    @Resource
    private IUserRepository iUserRepository;
    @Resource
    private IRoleService iRoleService;

    @Override
    public UserEntity loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = iUserRepository.findByUsernameAndDelFalse(s);
        if (user != null) {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for (RoleEntity role : user.getRole()) {
                if (role != null && role.getName() != null) {
                    for (PermissionEntity permission : role.getPermission()) {
                        if (permission != null && permission.getName() != null) {
                            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                            grantedAuthorities.add(grantedAuthority);
                        }
                    }
                }
            }
            return new UserEntity(user.getId(), user.getUsername(), user.getPassword(), user.getFullname(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + s + " do not exist!");
        }
    }

    public UserEntity save(String username, String password, String fullname, Integer organizationId) {

        UserEntity u = iUserRepository.findByUsernameAndDelFalse(username);
            u = new UserEntity();
            u.setUsername(username);
            u.setPassword(new BCryptPasswordEncoder().encode(password));
            u.setFullname(fullname);
        return iUserRepository.save(u);
    }

    @Override
    public Page<UserEntity> page(BasePage basePage) {
        return iUserRepository.findAllByDelFalse(basePage.getRequestPage());
    }

    @Override
    public Page<UserEntity> pageByCondition(BasePage basePage, List<Integer> orgIds) {
        Specification specification = (Specification) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (null != orgIds) {
                predicates.add(root.get("organizationId").in(orgIds));
            }
            predicates.add(cb.equal(root.get("del"), false));
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        Page all = iUserRepository.findAll(specification, basePage.getRequestPage());
        return all;
    }

    @Override
    public UserEntity findOne(Integer id) {
        return iUserRepository.findOne(id);
    }

    @Override
    public UserEntity enabled(Integer userId, Integer roleId, boolean grant) {
        UserEntity u = iUserRepository.findById(userId).get();
        RoleEntity r = iRoleService.findOne(roleId);
        if (grant) {
            u.getRole().add(r);
        } else {
            u.getRole().remove(r);
        }

        return iUserRepository.save(u);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return iUserRepository.findByUsernameAndDelFalse(username);
    }

    @Override
    public List<UserEntity> findAll() {
        return iUserRepository.findAllByDelFalse();
    }

    @Override
    public void delete(Integer id) {
        UserEntity u = iUserRepository.findById(id).get();
        Set<RoleEntity> roles = u.getRole();
        List<RoleEntity> all = iRoleService.findAll();
        for (RoleEntity r : all) {
            if (roles.contains(r)) {
                u.getRole().remove(r);
            }
        }
        u.setDel(true);
        iUserRepository.save(u);
    }

    @Override
    public UserEntity update(Integer id, String username, String password, String fullname) {
        UserEntity u;
        if (id == null) {
            u = new UserEntity();
        } else {
            u = iUserRepository.findOne(id);
        }
        u.setUsername(username);
        u.setPassword(new BCryptPasswordEncoder().encode(password));
        u.setFullname(fullname);
        return iUserRepository.save(u);
    }

    @Override
    public List<UserEntity> findByOrgId(List<Integer> integers) {
        return iUserRepository.findByOrgId(integers);
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        return iUserRepository.update(userEntity);
    }

}
