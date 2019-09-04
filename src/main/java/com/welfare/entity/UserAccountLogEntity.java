package com.welfare.entity;

import javax.persistence.Table;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/8/25 14:25
 * @Description:
 */
@Table(name = "user_account_log")
public class UserAccountLogEntity {
    private long id;
    private long userId;
    private String type;
    private long amount;
    private long createTime;
    private String welfareId;
    private String welfareName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getWelfareId() {
        return welfareId;
    }

    public void setWelfareId(String welfareId) {
        this.welfareId = welfareId;
    }

    public String getWelfareName() {
        return welfareName;
    }

    public void setWelfareName(String welfareName) {
        this.welfareName = welfareName;
    }
}
