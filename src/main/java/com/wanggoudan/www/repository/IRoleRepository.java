package com.wanggoudan.www.repository;

import com.wanggoudan.www.baseconfig.BaseRepository;
import com.wanggoudan.www.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRoleRepository extends BaseRepository<RoleEntity, Integer> {
    List<RoleEntity> findAllByDelFalse();

    Page<RoleEntity> findAllByDelFalse(Pageable pageable);
}
