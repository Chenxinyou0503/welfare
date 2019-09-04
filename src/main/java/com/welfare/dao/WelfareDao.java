package com.welfare.dao;

import com.welfare.MyMapper;
import com.welfare.entity.WelfareEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/10 14:48
 * @Description:
 */
public interface WelfareDao extends MyMapper<WelfareEntity> {
    /**
     * 修改项目状态
     *
     * @param welfareId
     * @param state
     * @return
     */
    @Update("update welfate set state = #{state,jdbcType=VARCHAR} where id =  #{welfareId,jdbcType=VARCHAR}")
    public int updateStatus(@Param("welfareId") String welfareId, @Param("state") String state);

    @Results(id = "query", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "welfareName", column = "welfare_name"),
            @Result(property = "welfareAccount", column = "welfare_account"),
            @Result(property = "welfareActualAccount", column = "welfare_actual_account"),
            @Result(property = "welfareSponsor", column = "welfare_sponsor"),
            @Result(property = "welfareTitle", column = "welfare_title"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "context", column = "context"),
            @Result(property = "buHash", column = "bu_hash"),
            @Result(property = "state", column = "state"),
            @Result(property = "image", column = "image"),
            @Result(property = "tag", column = "tag")
    })
    @Select("<script> " +
            "select * from welfare " +
            "<where>" +
            "<if test = 'state!=null and state!=0 '> " +
            " state = #{state,jdbcType=INTEGER}" +
            "</if>" +
            "</where>" +
            "</script>"
    )
    public List<WelfareEntity> selectListByState(@Param("state") Integer state);

    @Select("select * from welfare where id=#{id,jdbcType=VARCHAR}")
    public WelfareEntity selectWelfareOne(@Param("id") String id);

    @Update("update welfare set " +
            " welfare_actual_account =#{welfareActualAccount,jdbcType=INTEGER}" +
            "where id=#{id,jdbcType=VARCHAR}")
    public int updateWelfareAccount(@Param("id") long id,
                                    @Param("welfareActualAccount") long welfareActualAccount);
}
