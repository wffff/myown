package com.snkj.www.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.snkj.www.baseconfig.BaseEntity;
import com.snkj.www.enums.OrganizationTypeEnum;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by Danny on 2018/7/13.
 */
@Entity
@Table(name = "t_organization")
public class OrganizationEntity extends BaseEntity {
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", updatable = false, insertable = false)
    @Where(clause = "del=false")
    private OrganizationEntity parent;
    @Column(name = "parent_id")
    private Integer parentId;
    private OrganizationTypeEnum organizationType;
    private String name;
    private String companyName;
    private String description;
    private String memo;
    private String deviceName;

    @Transient
    private boolean checked=false;
    public OrganizationEntity() {
    }

    public OrganizationEntity(Integer parentId, OrganizationTypeEnum organizationType, String name, String companyName, String description, String memo) {
        this.parentId = parentId;
        this.organizationType = organizationType;
        this.name = name;
        this.companyName = companyName;
        this.description = description;
        this.memo = memo;
    }

    public OrganizationEntity(Integer parentId,OrganizationTypeEnum organizationType, String name, String deviceName) {
        this.parentId = parentId;
        this.organizationType=organizationType;
        this.name = name;
        this.deviceName = deviceName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public OrganizationEntity getParent() {
        return parent;
    }

    public void setParent(OrganizationEntity parent) {
        this.parent = parent;
    }

    public OrganizationTypeEnum getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(OrganizationTypeEnum organizationType) {
        this.organizationType = organizationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
