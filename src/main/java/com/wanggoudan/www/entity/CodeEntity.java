package com.wanggoudan.www.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wanggoudan.www.baseconfig.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by Danny on 2018/8/21.
 */
@Entity
@Table(name = "t_code")
public class CodeEntity extends BaseEntity {
    private String title;
    private String content;//正文
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false, insertable = false)
    @Where(clause = "del=false")
    private UserEntity user;
    @Column(name = "user_id")
    private Integer userId;
    public CodeEntity() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
