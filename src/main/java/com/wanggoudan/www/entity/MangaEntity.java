package com.wanggoudan.www.entity;

import com.wanggoudan.www.baseconfig.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_manga")
public class MangaEntity  extends BaseEntity {
    private String title;
    private String cover;
    @OneToMany(cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)//一对多,
    // fetch = FetchType.EAGER 这个是否开启延迟加载
    @JoinColumn(name = "mangaId")//添加主键
    @OrderBy("sort ASC")
    @Where(clause = "del=false")
    private List<MangaAttachEntity> attach = new ArrayList<>();
    public MangaEntity() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<MangaAttachEntity> getAttach() {
        return attach;
    }

    public void setAttach(List<MangaAttachEntity> attach) {
        this.attach = attach;
    }
}
