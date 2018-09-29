package com.snkj.www.dto;


import com.snkj.www.enums.OrganizationTypeEnum;

import java.util.List;

/**
 * Created by Danny on 2018/7/13.
 */
public class OrganizationDTO {
    List<Integer> ids;
    List<OrganizationTypeEnum> organizationType;
    Boolean del;

    public OrganizationDTO() {
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }

    public List<OrganizationTypeEnum> getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(List<OrganizationTypeEnum> organizationType) {
        this.organizationType = organizationType;
    }
}
