package com.springboot.cloud.dto;

import com.springboot.cloud.entity.MallGoodsCategory;
import lombok.Data;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/18 16:08
 * @Version 1.0
 */
@Data
public class CategoryGrand extends MallGoodsCategory {

    List<Long> grandsId;
}
