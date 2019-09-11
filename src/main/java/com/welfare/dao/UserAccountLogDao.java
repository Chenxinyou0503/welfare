package com.welfare.dao;

import com.welfare.MyMapper;
import com.welfare.entity.UserAccountLogEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/8/25 14:24
 * @Description:
 */
@Mapper
public interface UserAccountLogDao extends MyMapper<UserAccountLogEntity> {

    @Results(id = "queryAccountLog", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "type", column = "type"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "welfareId", column = "welfare_id"),
            @Result(property = "welfareName", column = "welfare_name"),
            @Result(property = "createTime", column = "create_time")

    })
    @Select("<script> " +
            "select * from user_account_log " +
            "<where>" +
            "<if test = 'type!=null and type!=0 '> " +
            " and type = #{type,jdbcType=INTEGER}" +
            "</if>" +
            "<if test = 'userId!=null and userId!=0 '> " +
            " and user_id = #{userId,jdbcType=INTEGER}" +
            "</if>" +
            "</where>" +
            "</script>"
    )
    public List<UserAccountLogEntity> selectListByState(@Param("state") Integer state, @Param("userId")long userId);
}
