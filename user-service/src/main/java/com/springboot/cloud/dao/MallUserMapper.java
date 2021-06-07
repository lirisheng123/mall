package com.springboot.cloud.dao;

import com.springboot.cloud.dto.UserParam;
import com.springboot.cloud.entity.MallUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.List;
import java.util.Map;

@Mapper
public interface MallUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(MallUser record);

    MallUser selectByPrimaryKey(Long userId);


    List<MallUser> selectAll();

    List<MallUser> selectPageByManyCondition(UserParam userParam);

    int updateByPrimaryKey(MallUser record);

    int updateLockStatusByUserId (Map<String,Object> map);

    @Select("SELECT  * FROM  mall_user WHERE  login_name= #{username} ")
    MallUser selectByLoginName(String username);

    @Update("UPDATE  mall_user  SET is_deleted=1 WHERE user_id=#{userId}  ")
    int updateDeleteByUserId(Long userId);
}