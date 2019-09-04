package com.welfare.entity;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/10 14:41
 * @Description: 用户
 */
@Table(name = "user")
public class UserEntity {
    @Column(name = "id")
    @KeySql(useGeneratedKeys = true)

    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "role")
    private String role;
    @Column(name = "bu_id")
    private String buId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBuId() {
        return buId;
    }

    public void setBuId(String buId) {
        this.buId = buId;
    }
}
