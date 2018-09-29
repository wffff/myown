package com.snkj.www.repository;

import com.snkj.www.baseconfig.BaseRepository;
import com.snkj.www.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Danny on 2018/8/21.
 */
public interface IUserRepository extends BaseRepository<UserEntity, Integer> {
    UserEntity findByUsernameAndDelFalse(String username);
    Page<UserEntity> findAllByDelFalse(Pageable pageable);
    @Query("from UserEntity where del=false and organizationId in ?1")
    List<UserEntity> findByOrgId(List<Integer> orgIds);

    List<UserEntity> findAllByDelFalse();
}
