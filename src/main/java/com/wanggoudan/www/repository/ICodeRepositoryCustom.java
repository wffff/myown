package com.wanggoudan.www.repository;

import com.wanggoudan.www.baseconfig.BasePage;
import com.wanggoudan.www.entity.CodeEntity;
import org.springframework.data.domain.Page;

public interface ICodeRepositoryCustom {
    Page<CodeEntity> page(BasePage basePage,String title);
}
