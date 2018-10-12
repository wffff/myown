package com.wanggoudan.www.service;

import com.wanggoudan.www.baseconfig.BasePage;
import com.wanggoudan.www.entity.VideoEntity;
import org.springframework.data.domain.Page;

public interface IVideoService {
    Page<VideoEntity> page(BasePage basePage);

    VideoEntity save(String title, String url);

    VideoEntity findOne(Integer id);
}
