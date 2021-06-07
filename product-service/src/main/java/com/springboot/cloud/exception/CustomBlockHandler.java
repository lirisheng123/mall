package com.springboot.cloud.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springboot.cloud.dto.EsProduct;
import com.springboot.cloud.util.CommonResult;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/5/2 16:10
 * @Version 1.0
 */
public class CustomBlockHandler {

    /**
     *
     * 注意:要保证形参,返回类型一致
     *      该方法一定要被static进行修饰
     */
    public static  CommonResult<List<EsProduct>> handleException(BlockException exception){
        return CommonResult.failed("流量访问已达到最大");
    }
}