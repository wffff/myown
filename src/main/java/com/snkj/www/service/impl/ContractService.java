package com.snkj.www.service.impl;

import com.snkj.www.baseconfig.BasePage;
import com.snkj.www.entity.ContractEntity;
import com.snkj.www.repository.IContractRepository;
import com.snkj.www.repository.IContractRepositoryCustom;
import com.snkj.www.service.IContractService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Danny on 2018/8/21.
 */
@Service
public class ContractService implements IContractService {

    @Resource
    private IContractRepository iContractRepository;

    @Override
    public Page<ContractEntity> page(BasePage basePage, String companyName , String title) {
        return iContractRepository.page(basePage,companyName,title);
    }

    @Override
    public ContractEntity save(String content, String companyName, String contactMan, String phone, String fax, String saleman, Double amount, Date time, String payMethod, String title, String remarks) {
        ContractEntity c = new ContractEntity();
        c.setContent(content);
        c.setCompanyName(companyName);
        c.setContactman(contactMan);
        c.setPhone(phone);
        c.setFax(fax);
        c.setSaleman(saleman);
        c.setAmount(amount);
        //c.setTime(new Date());
        c.setTime(time);
        c.setPayMethod(payMethod);
        c.setTitle(title);
        c.setRemarks(remarks);

        return iContractRepository.save(c);
    }

    @Override
    public ContractEntity update(Integer id, String content, String companyName, String contactMan, String phone, String fax, String saleman, Double amount, Date time, String payMethod, String title, String remarks) {
        ContractEntity c;
        if (id == null) {
            c = new ContractEntity();
        } else {
            c = iContractRepository.findById(id).get();
        }
        c.setContent(content);
        c.setCompanyName(companyName);
        c.setContactman(contactMan);
        c.setPhone(phone);
        c.setFax(fax);
        c.setSaleman(saleman);
        c.setAmount(amount);
        //c.setTime(new Date());

        c.setTime(time);
        c.setPayMethod(payMethod);
        c.setTitle(title);
        c.setRemarks(remarks);

        return iContractRepository.save(c);
    }

    @Override
    public ContractEntity findById(Integer id) {
        Optional<ContractEntity> c = iContractRepository.findById(id);
        return c.get();
    }

    public void delete(Integer id) {
        ContractEntity c;
        c = iContractRepository.findById(id).get();
        c.setDel(true);
        iContractRepository.save(c);
    }

   /* @Override
    public List<ContractEntity> findAllByNameLike(String key) {
        return iContractRepository.findAllByCompanyNameLike("%" + key + "%");
    }
*/
}
