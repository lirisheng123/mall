package com.springboot.cloud.dto;

import com.springboot.cloud.entity.MallGoodsInfo;
import lombok.Data;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/3/21 17:17
 * @Version 1.0
 */
@Data
public class MallGoodsInsertParam  extends MallGoodsInfo {

    List<ProductParam> productParams;

    List<ProductProperty> productProperties;




}
