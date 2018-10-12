package com.wanggoudan.www.service.impl;

import com.wanggoudan.www.baseconfig.BasePage;
import com.wanggoudan.www.entity.MangaAttachEntity;
import com.wanggoudan.www.entity.MangaEntity;
import com.wanggoudan.www.repository.IMangaRepository;
import com.wanggoudan.www.service.IMangaService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MangaService implements IMangaService {
    @Resource
    private IMangaRepository iMangaRepository;

    @Override
    public MangaEntity findOne(Integer id) {
        return iMangaRepository.findOne(id);
    }

    @Override
    public Page<MangaEntity> findAll(BasePage basePage) {
        return iMangaRepository.findAll(basePage.getRequestPage());
    }

    @Override
    public MangaEntity save(String title, String cover, List<String> content) {
        MangaEntity manga=new MangaEntity();
        manga.setTitle(title);
        manga.setCover(cover);
        List<MangaAttachEntity> list=new ArrayList<>();
        MangaAttachEntity mangaAttach;
        for (int i=0;i<content.size();i++){
            mangaAttach=new MangaAttachEntity();
            mangaAttach.setSort(i+1);
            mangaAttach.setUrl(content.get(i));
            mangaAttach.setTime(new Date());
            list.add(mangaAttach);
        }
        manga.setAttach(list);
        return iMangaRepository.save(manga);
    }
}
