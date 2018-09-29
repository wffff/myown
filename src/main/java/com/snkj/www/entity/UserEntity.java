package com.snkj.www.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.snkj.www.baseconfig.BaseEntity;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Danny on 2018/8/21.
 */
@Entity
@Table(name = "t_user")
public class UserEntity extends BaseEntity implements UserDetails {
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id", updatable = false, insertable = false)
    @Where(clause = "del=false")
    private OrganizationEntity organization;
    @Column(name = "organization_id")
    private Integer organizationId;
    private String username;
    private String password;
    private String mobile;
    private String email;
    private String fullname;
    @Transient
    private Boolean exist;
    @Transient
    private Set<GrantedAuthority> authorities;

    @Transient
    private String organizationName;

    private Boolean enabled=true;
    private Boolean expired=false;
    private Boolean locked=false;
    private Boolean limited=false;

    //    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    @OrderBy(value = "id DESC")
    @Where(clause = "del = false")
    private Set<RoleEntity> role = new HashSet<>();

    public UserEntity() {
    }

    public UserEntity(Integer id,Integer organizationId,OrganizationEntity organization, String username, String password,String fullname, Set<GrantedAuthority> grantedAuthorities) {
        super.id=id;
        this.organizationId=organizationId;
        this.organization=organization;
        this.username=username;
        this.password=password;
        this.fullname=fullname;
        this.authorities=grantedAuthorities;
    }

    public String getOrganizationName() {
        if (null!=organization){
            return organization.getName();
        }
        return "无组织状态,请分配组织";
    }

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public Set<RoleEntity> getRole() {
        return role;
    }

    public void setRole(Set<RoleEntity> role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !limited;
    }

    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
