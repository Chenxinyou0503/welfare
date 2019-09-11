package com.welfare.dao;

import com.welfare.MyMapper;
import com.welfare.entity.UserAccountEntity;
import org.apache.ibatis.annotations.*;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/10 14:48
 * @Description:
 */
@Mapper
public interface UserAccountDao extends MyMapper<UserAccountEntity> {


    @Results(id = "queryUserAccount", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "user_id", column = "userId"),
            @Result(property = "money", column = "money"),
            @Result(property = "code", column = "code")
    })
    @Select("select * from user_account where user_id =#{userId,jdbcType=VARCHAR}")
    UserAccountEntity selectByUserId(long userId);

    @Update("update user_account set money = #{money,jdbcType=INTEGER} where user_id =#{userId}")
    int updateUserAccount(@Param("money") long money, @Param("userId") long userId);
}
