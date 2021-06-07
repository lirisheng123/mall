package com.springboot.cloud.dto;

import com.springboot.cloud.domain.EsProductParamValue;
import lombok.Data;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/4/11 16:55
 * @Version 1.0
 */
@Data
public class EsProductQuery {

    String keyword;
    List<Long> brandId;
    List<Long> productCategoryId;
    List<EsProductParams> productParams;
    Integer pageNum =0;
    Integer pageSize=5;
    Integer sort=0;




}
