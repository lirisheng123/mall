package com.springboot.cloud.dao;


import com.springboot.cloud.dto.ReturnApplyParams;
import com.springboot.cloud.entity.MallOrderReturnApply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface MallOrderReturnApplyMapper {
    int deleteByPrimaryKey(Long orderReturnApplyId);

    int insert(MallOrderReturnApply record);

    MallOrderReturnApply selectByPrimaryKey(Long orderReturnApplyId);

    List<MallOrderReturnApply> selectAll();

    int updateByPrimaryKey(MallOrderReturnApply record);

    @Select("SELECT  * FROM  mall_order_return_apply WHERE order_id=#{orderId} ")
    MallOrderReturnApply selectByOrderId(Long orderId);

    List<MallOrderReturnApply> selectList(ReturnApplyParams returnApplyParams);
}