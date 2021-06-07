package com.springboot.cloud.dao;



import com.springboot.cloud.entity.MallUserAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface MallUserAddressMapper {
    int deleteByPrimaryKey(Long addressId);

    int insert(MallUserAddress record);

    MallUserAddress selectByPrimaryKey(Long addressId);

    List<MallUserAddress> selectAll();

    int updateByPrimaryKey(MallUserAddress record);

    @Select("SELECT  * FROM  mall_user_address WHERE  user_id=#{userId} ")
    List<MallUserAddress> selectListByUserId(Long userId);

    @Update("UPDATE mall_user_address SET  default_flag=#{flag} WHERE  address_id=#{addressId} ")
    int updateDefaultFlag(@Param("addressId") Long addressId,@Param("flag") Long flag);

   @Select("SELECT  address_id FROM  mall_user_address WHERE  user_id=#{userId} AND  default_flag=1")
    Long selectAnyDefaultFlagByUserId(Long userId);

    @Select("SELECT  * FROM  mall_user_address WHERE  user_id=#{userId} AND  default_flag=1")
    MallUserAddress selectDefaultByUserId(Long userId);
}