package com.wanggoudan.www.service;

import com.wanggoudan.www.baseconfig.BasePage;
import com.wanggoudan.www.entity.MangaEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IMangaService {
    MangaEntity findOne(Integer id);
    Page<MangaEntity> findAll(BasePage basePage);

    MangaEntity save(String title, String cover, List<String> content);
}
