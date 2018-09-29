package com.wanggoudan.www.repository;

import com.wanggoudan.www.baseconfig.BaseRepository;
import com.wanggoudan.www.entity.PermissionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPermissionRepository extends BaseRepository<PermissionEntity,Integer> {
    List<PermissionEntity> findAllByDelFalse();

    Page<PermissionEntity> findAllByDelFalse(Pageable pageable);
}
