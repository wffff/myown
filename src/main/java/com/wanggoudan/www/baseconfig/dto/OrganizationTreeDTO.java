package com.wanggoudan.www.baseconfig.dto;



import com.wanggoudan.www.baseconfig.util.TreeUtils;
import com.wanggoudan.www.entity.OrganizationEntity;
import com.wanggoudan.www.enums.OrganizationTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam Yao on 2017/12/16.
 */
public class OrganizationTreeDTO extends TreeUtils.BaseTreeGrid {

    private OrganizationTypeEnum organizationType;
    private String name;
    private String companyName;
    private String description;
    private String memo;

    public OrganizationTreeDTO(Integer id, Integer parentId, OrganizationTypeEnum organizationType, String name, String companyName, String description, String memo) {
        super(id, parentId);
        this.organizationType = organizationType;
        this.name = name;
        this.companyName = companyName;
        this.description = description;
        this.memo = memo;
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


    @SuppressWarnings("Duplicates")
    public static List<OrganizationTreeDTO> formatTreeDTOByEntity(List<OrganizationEntity> list) {
        List<OrganizationTreeDTO> dtoList = new ArrayList<>();
        for (OrganizationEntity org : list) {
            Integer pid = null;
            OrganizationEntity parent = org.getParent();
            if (null != parent) {
                pid = parent.getId();
            }
            dtoList.add(new OrganizationTreeDTO(org.getId(), pid, org.getOrganizationType(), org.getName(), org.getCompanyName(), org.getDescription(),
                    org.getMemo()));
        }
        return TreeUtils.formatTree(dtoList, false);
    }

}
