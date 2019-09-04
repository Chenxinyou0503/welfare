package com.welfare.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/8/25 14:44
 * @Description:
 */
public class PageData<T> implements Serializable {

    private long total = 0;
    private List<T> list;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
