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
    @Insert("insert into user(username,password,phone) value(#{username,jdbcType=VARCHAR}," +
            "#{password,jdbcType=VARCHAR},#{phone,jdbcType=VARCHAR})")
    @Override
    int insert(UserEntity entity);

    @Results(id = "queryUser", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "email", column = "email"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "role", column = "role"),
            @Result(property = "bu_id", column = "bu_id")
    })
    @Select("select * from user where username = #{username,jdbcType=VARCHAR} limit 0,1")
    UserEntity query(String username);

    @ResultMap(value = "queryUser")
    @Select("select * from user where username = #{username,jdbcType=VARCHAR} limit 0,1")
    UserEntity queryOne(String username);

    @Update("update user SET password =#{password,jdbcType=VARCHAR} where id = #{userId}")
    int updatePassword(@Param("userId") long userId, @Param("password") String password);
}
