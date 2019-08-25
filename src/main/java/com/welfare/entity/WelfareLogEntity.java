package com.welfare.entity;

import javax.persistence.Table;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/10 14:42
 * @Description:
 */
@Table(name = "welfare_log")
public class WelfareLogEntity {
    private long id ;
    private String welfareSponsor;
    private long welfareId;
    private String welfareTitle;
    private long createTime;
    private String code;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWelfareSponsor() {
        return welfareSponsor;
    }

    public void setWelfareSponsor(String welfareSponsor) {
        this.welfareSponsor = welfareSponsor;
    }

    public long getWelfareId() {
        return welfareId;
    }

    public void setWelfareId(long welfareId) {
        this.welfareId = welfareId;
    }

    public String getWelfareTitle() {
        return welfareTitle;
    }

    public void setWelfareTitle(String welfareTitle) {
        this.welfareTitle = welfareTitle;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
