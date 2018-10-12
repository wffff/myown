package com.wanggoudan.www.service.impl;

import com.wanggoudan.www.baseconfig.BasePage;
import com.wanggoudan.www.entity.VideoEntity;
import com.wanggoudan.www.repository.IVideoRepository;
import com.wanggoudan.www.service.IVideoService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class VideoService implements IVideoService {
    @Resource
    private IVideoRepository iVideoRepository;

    @Override
    public Page<VideoEntity> page(BasePage basePage) {
        return iVideoRepository.findAll(basePage.getRequestPage());
    }

    @Override
    public VideoEntity save(String title, String url) {
        VideoEntity videoEntity=new VideoEntity();
        videoEntity.setTitle(title);
        videoEntity.setUrl(url);
        return iVideoRepository.save(videoEntity);
    }

    @Override
    public VideoEntity findOne(Integer id) {
        return iVideoRepository.findOne(id);
    }
}
