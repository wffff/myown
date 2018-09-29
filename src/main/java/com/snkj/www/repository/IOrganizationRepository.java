package com.snkj.www.repository;


import com.snkj.www.baseconfig.BaseRepository;
import com.snkj.www.entity.OrganizationEntity;

import java.util.List;

/**
 * Created by Danny on 2018/7/13.
 */
public interface IOrganizationRepository extends BaseRepository<OrganizationEntity, Integer>, IOrganizationRepositoryCustom {
    List<OrganizationEntity> findAllByDelFalse();
}
