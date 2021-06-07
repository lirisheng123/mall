package com.springboot.cloud.dto;

import com.springboot.cloud.entity.MallGrand;
import lombok.Data;

/**
 * @Author: lirisheng
 * @Date: 2021/3/19 22:40
 * @Version 1.0
 */
@Data
public class GrandAndCateGrandId extends MallGrand {
    Long categoryGrandId;
}
