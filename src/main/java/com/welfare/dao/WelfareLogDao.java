package com.welfare.dao;

import com.welfare.MyMapper;
import com.welfare.entity.WelfareLogEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/10 14:49
 * @Description:
 */
public interface WelfareLogDao extends MyMapper<WelfareLogEntity> {

    @Results(id = "queryLog", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "welfareSponsor", column = "welfare_sponsor"),
            @Result(property = "welfareId", column = "welfare_id"),
            @Result(property = "welfareTitle", column = "welfare_title"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "code", column = "code")
    })
    @Select("select * from welfare_log where welfare_id =#{welfareId,jdbcType=VARCHAR}")
    List<WelfareLogEntity> selectListByWelfareId(String welfareId);
}
