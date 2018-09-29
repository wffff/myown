package com.snkj.www.repository;

import com.snkj.www.baseconfig.BaseRepository;
import com.snkj.www.entity.PermissionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPermissionRepository extends BaseRepository<PermissionEntity,Integer> {
    List<PermissionEntity> findAllByDelFalse();

    Page<PermissionEntity> findAllByDelFalse(Pageable pageable);
}
