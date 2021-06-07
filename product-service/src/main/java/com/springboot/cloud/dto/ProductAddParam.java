package com.springboot.cloud.dto;

import com.springboot.cloud.entity.MallGoodsInfo;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/3/20 21:01
 * @Version 1.0
 */
@Data
@ToString(callSuper = true)
public class ProductAddParam extends MallGoodsInfo {

    //与品牌相关联
    Long grandId;
    List<ProductPropertyRelate> productPropertyRelates;

    //与库存
    List<ProductProperty> productProperties;

    //与参数
    List<ProductParam>  productParams;


    public static void main(String[] arg){
        ProductAddParam productAddParam = new ProductAddParam();

        System.out.println(productAddParam);
    }

}
