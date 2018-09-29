package com.snkj.www.entity;

import com.snkj.www.baseconfig.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Danny on 2018-08-29.
 */
@Entity
@Table(name = "t_attachment")
public class AttachmentEntity extends BaseEntity {
    private Integer contractId;
    private String type;
    private String content;
    private String url;

    public AttachmentEntity() {
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
