package com.snkj.www.entity;

import com.snkj.www.baseconfig.BaseEntity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Danny on 2018/8/21.
 */
@Entity
@Table(name = "t_contract")
public class ContractEntity extends BaseEntity {
    private String content;//正文
    private String companyName;//签订单位
    //private Integer contactId;//联系人id
    private String contactman;//联系人
    private String saleman;//业务员
   // private Integer salemanId;//业务员
    private String phone;//电话
    private String fax;//传真
    private Double amount;//金额

    private String payMethod;//付款方式
    private String title;//标题
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;//签订时间

    @Transient
    private String dateTime;
    private String remarks;//备注


    public String getPayMethod() { return payMethod; }

    public void setPayMethod(String payMethod) { this.payMethod = payMethod; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    @Override
    public Date getTime() { return time; }

    @Override
    public void setTime(Date time) { this.time = time; }

    public String getRemarks() { return remarks; }

    public void setRemarks(String remarks) { this.remarks = remarks; }


    public ContractEntity() {
    }

    public String getDateTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(time.getTime());
        return format1;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactman() {
        return contactman;
    }

    public void setContactman(String contactman) {
        this.contactman = contactman;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getSaleman() {
        return saleman;
    }

    public void setSaleman(String saleman) {
        this.saleman = saleman;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
