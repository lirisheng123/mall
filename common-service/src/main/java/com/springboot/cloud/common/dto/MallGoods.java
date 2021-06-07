package com.springboot.cloud.common.dto;


import com.springboot.cloud.common.entity.MallGoodsInfo;
import com.springboot.cloud.common.entity.MallGoodsProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/18 15:08
 * @Version 1.0
 */
@Data
public class MallGoods extends MallGoodsInfo {

    List<MallGoodsProperty> mallGoodsProperties;
}
