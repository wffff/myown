package com.wanggoudan.www.entity;

import com.wanggoudan.www.baseconfig.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_video")
public class VideoEntity extends BaseEntity {
    private String title;
    private String url;

    public VideoEntity() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
