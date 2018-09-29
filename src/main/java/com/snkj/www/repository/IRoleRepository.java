package com.snkj.www.repository;

import com.snkj.www.baseconfig.BaseRepository;
import com.snkj.www.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRoleRepository extends BaseRepository<RoleEntity, Integer> {
    List<RoleEntity> findAllByDelFalse();

    Page<RoleEntity> findAllByDelFalse(Pageable pageable);
}
