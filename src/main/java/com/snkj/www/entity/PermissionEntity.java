package com.snkj.www.entity;

import com.snkj.www.baseconfig.BaseEntity;

import javax.persistence.*;


@Entity
@Table(name = "t_permission")
public class PermissionEntity extends BaseEntity {
    private String name;
    private String description;
    @Transient
    private boolean enabled;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
