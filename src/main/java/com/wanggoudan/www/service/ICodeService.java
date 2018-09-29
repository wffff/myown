package com.wanggoudan.www.service;

import com.wanggoudan.www.baseconfig.BasePage;
import com.wanggoudan.www.entity.CodeEntity;
import org.springframework.data.domain.Page;

public interface ICodeService {
    Page<CodeEntity> page(BasePage basePage,String title);

    CodeEntity save(String title, String content);

    CodeEntity update(Integer id, String title, String content);

    CodeEntity findById(Integer id);

    void delete(Integer id);

}
