package com.snkj.www.service;

import com.snkj.www.entity.AttachmentEntity;

import java.util.List;

/**
 * Created by Danny on 2018-08-29.
 */
public interface IAttachmentService {
    List<AttachmentEntity> findAllByContractId(Integer contractId);

   AttachmentEntity save(String type, String content, String url,Integer contractId);

   void delete(Integer id);
}
