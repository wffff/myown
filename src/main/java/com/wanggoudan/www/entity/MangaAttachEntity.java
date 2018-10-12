package com.wanggoudan.www.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wanggoudan.www.baseconfig.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "t_manga_attach")
public class MangaAttachEntity extends BaseEntity {
    private String url;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mangaId", updatable = false, insertable = false)
    @Where(clause = "del=false")
    private MangaEntity manga;
    private Integer mangaId;
    private Integer sort;

    public MangaAttachEntity() {
    }

    public Integer getMangaId() {
        return mangaId;
    }

    public void setMangaId(Integer mangaId) {
        this.mangaId = mangaId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MangaEntity getManga() {
        return manga;
    }

    public void setManga(MangaEntity manga) {
        this.manga = manga;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
