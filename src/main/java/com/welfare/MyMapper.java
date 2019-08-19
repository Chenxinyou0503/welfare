package com.welfare;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/8/17 14:57
 * @Description:
 */
public interface MyMapper<T> extends Mapper<T> , MySqlMapper<T> {
}
