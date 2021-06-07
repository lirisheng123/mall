package com.springboot.cloud.dto;

import com.springboot.cloud.entity.MallCategoryProperty;
import com.springboot.cloud.entity.MallGoodsCategory;
import lombok.Data;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/3/20 21:32
 * @Version 1.0
 */
@Data
public class CategoryParentAndChird extends MallGoodsCategory {

    List<MallGoodsCategory> children;

}
