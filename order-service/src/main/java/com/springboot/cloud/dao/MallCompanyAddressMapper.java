package com.springboot.cloud.dao;


import com.springboot.cloud.entity.MallCompanyAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.checkerframework.checker.guieffect.qual.UI;
import org.springframework.stereotype.Service;

import java.util.List;
@Mapper
public interface MallCompanyAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MallCompanyAddress record);

    MallCompanyAddress selectByPrimaryKey(Long id);

    List<MallCompanyAddress> selectAll();

    int updateByPrimaryKey(MallCompanyAddress record);

    @Select("SELECT id FROM  mall_company_address WHERE   send_status=1")
    MallCompanyAddress judgeSendDefault();

    @Update("UPDATE  mall_company_address SET send_status=#{sendStatus} WHERE  id=#{id}")
    int changSendStatus(@Param("id") Long id, @Param("sendStatus") Integer sendStatus );

    @Select("SELECT id FROM  mall_company_address WHERE   receive_status=1")
    MallCompanyAddress judgeReceiveDefault();

    @Update("UPDATE  mall_company_address SET receive_status=#{receiveStatus} WHERE  id=#{id}")
    int changReceiveStatus(@Param("id") Long id, @Param("receiveStatus") Integer receiveStatus );


}