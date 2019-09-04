package com.welfare.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/10 14:42
 * @Description:
 */
@Repository
@Table(name = "welfare")
public class WelfareEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "welfare_name")
    private String welfareName;
    @Column(name = "welfare_account")
    private long welfareAccount;
    @Column(name = "welfare_actual_account")
    private long welfareActualAccount;
    @Column(name = "welfare_sponsor")
    private String welfareSponsor;
    @Column(name = "welfare_title")
    private String welfareTitle;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "end_time")
    private Date endTime;
    @Column(name = "context")
    private String context;
    @Column(name = "bu_hash")
    private String buHash;
    @Column(name = "state")
    private Integer state;
    @Column(name = "tag")
    private String tag;


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWelfareName() {
        return welfareName;
    }

    public void setWelfareName(String welfareName) {
        this.welfareName = welfareName;
    }

    public long getWelfareAccount() {
        return welfareAccount;
    }

    public void setWelfareAccount(long welfareAccount) {
        this.welfareAccount = welfareAccount;
    }

    public long getWelfareActualAccount() {
        return welfareActualAccount;
    }

    public void setWelfareActualAccount(long welfareActualAccount) {
        this.welfareActualAccount = welfareActualAccount;
    }

    public String getWelfareSponsor() {
        return welfareSponsor;
    }

    public void setWelfareSponsor(String welfareSponsor) {
        this.welfareSponsor = welfareSponsor;
    }

    public String getWelfareTitle() {
        return welfareTitle;
    }

    public void setWelfareTitle(String welfareTitle) {
        this.welfareTitle = welfareTitle;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getBuHash() {
        return buHash;
    }

    public void setBuHash(String buHash) {
        this.buHash = buHash;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
