package com.welfare.entity;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/10 14:42
 * @Description:
 */
public class WelfareEntity {
    private long id;
    private String welfareName;
    private long welfareAccount;
    private long welfareActualAccount;
    private String welfareSponsor;
    private String welfareTitle;
    private long createTime;
    private long endTime;
    private String context;
    private String buHash;
    private int state;
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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
