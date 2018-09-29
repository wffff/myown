package com.snkj.www.repository;

import com.snkj.www.baseconfig.BaseRepository;
import com.snkj.www.entity.AttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Danny on 2018/8/21.
 */
public interface IAttachmentRepository extends BaseRepository<AttachmentEntity, Integer> {
    List<AttachmentEntity> findAllByContractIdAndDelFalse(Integer contractId);
}
