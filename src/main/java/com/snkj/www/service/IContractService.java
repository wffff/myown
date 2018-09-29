package com.snkj.www.service;

import com.snkj.www.baseconfig.BasePage;
import com.snkj.www.entity.ContractEntity;
import org.springframework.data.domain.Page;

import java.util.Date;

/**
 * Created by Danny on 2018/8/21.
 *
 *  ContractEntity c=new ContractEntity();
 *         c.setDel(false);
 *         return iContractRepository.findAll(Example.of(c),PageRequest.of(page-1,limit));
 *
 *
 */
public interface IContractService {
    Page<ContractEntity> page(BasePage basePage, String companyName , String title);

    ContractEntity save(String content, String companyName, String contactMan, String phone, String fax, String saleman, Double amount, Date time, String payMethod, String title, String remarks);

    ContractEntity update(Integer id,String content, String companyName, String contactMan, String phone, String fax, String saleman, Double amount, Date time, String payMethod, String title, String remarks);

    ContractEntity findById(Integer id);

    void delete(Integer id);

    //List<ContractEntity> findAllByNameLike(String key);
}
