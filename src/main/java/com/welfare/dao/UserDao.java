package com.welfare.dao;

import com.welfare.MyMapper;
import com.welfare.entity.UserEntity;
import org.apache.ibatis.annotations.*;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/10 14:41
 * @Description:
 */
@Mapper
public interface UserDao extends MyMapper<UserEntity> {
//    @Insert("insert into user(username,password,phone) value(#{username,jdbcType=VARCHAR}," +
//            "#{password,jdbcType=VARCHAR},#{phone,jdbcType=VARCHAR})")
//    int insert(UserEntity entity);
//
//    @Results(id = "query", value = {
//            @Result(property = "id", column = "id"),
//            @Result(property = "welfareName", column = "welfare_name"),
//            @Result(property = "welfareAccount", column = "welfare_account"),
//            @Result(property = "welfareActualAccount", column = "welfare_actual_account"),
//            @Result(property = "welfareSponsor", column = "welfare_sponsor"),
//            @Result(property = "welfareTitle", column = "welfare_title"),
//            @Result(property = "createTime", column = "create_time"),
//            @Result(property = "endTime", column = "end_time"),
//            @Result(property = "context", column = "context"),
//            @Result(property = "buHash", column = "bu_hash"),
//            @Result(property = "state", column = "state"),
//            @Result(property = "image", column = "image"),
//            @Result(property = "tag", column = "tag")
//    })
//    @Select("select * from user where username = #{username,jdbcType=VARCHAR} limit 0,1")
//    UserEntity query(String username);
//
//    @ResultMap(value = "query")
//    @Select("select * from user where username = #{username,jdbcType=VARCHAR} limit 0,1")
//    UserEntity queryOne(String username);
}
