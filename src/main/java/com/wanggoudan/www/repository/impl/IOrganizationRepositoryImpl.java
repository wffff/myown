package com.wanggoudan.www.repository.impl;

import com.wanggoudan.www.baseconfig.dto.OrganizationDTO;
import com.wanggoudan.www.entity.OrganizationEntity;
import com.wanggoudan.www.enums.OrganizationTypeEnum;
import com.wanggoudan.www.repository.IOrganizationRepository;
import com.wanggoudan.www.repository.IOrganizationRepositoryCustom;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Danny on 2018/7/13.
 */
public class IOrganizationRepositoryImpl implements IOrganizationRepositoryCustom {
    @Resource
    private IOrganizationRepository iOrganizationRepository;

    private Specification<OrganizationEntity> condition(OrganizationDTO organization) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();
            if (organization != null) {
                // 删除
                if (null != organization.getIds() && !organization.getIds().isEmpty())
                    predicates.add(root.get("id").in(organization.getIds()));
                if (null != organization.getOrganizationType() && !organization.getOrganizationType().isEmpty())
                    predicates.add(root.get("organizationType").in(organization.getOrganizationType()));
                if (organization.getDel() != null) predicates.add(cb.equal(root.get("del"), organization.getDel()));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    @Override
    public List<OrganizationEntity> listDevice(List<Integer> list) {
        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setIds(list);
        organizationDTO.setDel(false);
        List<OrganizationTypeEnum> list1=new ArrayList();
        list1.add(OrganizationTypeEnum.DEVICE);
        organizationDTO.setOrganizationType(list1);
        return iOrganizationRepository.findAll(condition(organizationDTO));
    }
}
