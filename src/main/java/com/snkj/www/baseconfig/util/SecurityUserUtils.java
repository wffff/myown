package com.snkj.www.baseconfig.util;

import com.snkj.www.baseconfig.UserException;
import com.snkj.www.entity.OrganizationEntity;
import com.snkj.www.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Adam.yao on 2017/11/10.
 */
public class SecurityUserUtils {

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public static UserEntity getSecurityUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            Authentication authentication = context.getAuthentication();
            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                if (principal instanceof UserEntity) {
                    return (UserEntity) principal;
                }
            }
        }
        throw new UserException("登录信息有误");
    }

    public static OrganizationEntity getOrganization(){
        UserEntity securityUser = getSecurityUser();
        return securityUser.getOrganization();
    }

    public static Integer getUserId(){
        UserEntity securityUser = getSecurityUser();
        return securityUser.getId();
    }

    public static boolean isAdmin(){
        Integer userId = getUserId();
        if (userId.equals(1)){
            return true;
        }else {
            return false;
        }
    }
}
