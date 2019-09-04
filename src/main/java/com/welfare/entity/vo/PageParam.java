package com.welfare.entity.vo;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/9/1 10:45
 * @Description:
 */
public class PageParam {
    private int pageSize = 10;
    private int pageNo = 1;

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }
}
