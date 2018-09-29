package com.snkj.www.repository.impl;

import com.snkj.www.baseconfig.BasePage;
import com.snkj.www.entity.ContractEntity;
import com.snkj.www.repository.IContractRepository;
import com.snkj.www.repository.IContractRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class IContractRepositoryImpl implements IContractRepositoryCustom {
    @Resource
    private IContractRepository iContractRepository;
    @Override
    public Page<ContractEntity> page(BasePage basePage,String companyName ,String title) {
        Specification<ContractEntity> specification = (Specification<ContractEntity>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();//List<Predicate> 条件集合；
            if (companyName!=null&&companyName!=""){
                predicates.add(cb.like(root.get("companyName"), "%"+companyName.trim()+"%"));//往条件集合中添加条件，companyName.trim()是去掉输入companyName时带有的空格
            }
            if (title!=null&&title!=""){
                predicates.add(cb.like(root.get("title"), "%"+title.trim()+"%"));//往条件集合中添加条件，companyName.trim()是去掉输入companyName时带有的空格
            }
            predicates.add(cb.equal(root.get("del"), false));
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return iContractRepository.findAll(specification, basePage.getRequestPage());
    }
}
