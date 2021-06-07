package com.springboot.cloud.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/4/11 17:04
 * @Version 1.0
 */
@Data
public class EsProductParams {

    String goodsParamName;
    List<String> goodsParamValue;
}
