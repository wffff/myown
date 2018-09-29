package com.wanggoudan.www.repository.impl;

import com.wanggoudan.www.baseconfig.BasePage;
import com.wanggoudan.www.entity.CodeEntity;
import com.wanggoudan.www.repository.ICodeRepository;
import com.wanggoudan.www.repository.ICodeRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ICodeRepositoryImpl implements ICodeRepositoryCustom {
    @Resource
    private ICodeRepository iCodeRepository;

    @Override
    public Page<CodeEntity> page(BasePage basePage,String title) {
        Specification<CodeEntity> specification = (Specification<CodeEntity>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();//List<Predicate> 条件集合；
            if (title!=null&&title!=""){
                predicates.add(cb.like(root.get("title"), "%"+title.trim()+"%"));//往条件集合中添加条件，companyName.trim()是去掉输入companyName时带有的空格
            }
            predicates.add(cb.equal(root.get("del"), false));
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return iCodeRepository.findAll(specification, basePage.getRequestPage());
    }
}
