package com.springboot.cloud.service;

import com.springboot.cloud.entity.MallSecondProperty;
import com.springboot.cloud.util.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/4/25 16:19
 * @Version 1.0
 */
@FeignClient(contextId ="second-service",value = "second-service")
@RequestMapping("/second")
public interface SecondService {

    /**
     * 初始化 预热
     * @return
     */
    @PostMapping("/initDBAndRedis")
    CommonResult initRedisStock(@RequestBody MallSecondProperty mallSecondProperty);

    /**
     * 关闭缓存
     * @return
     */
    @GetMapping("/deleteRedisStock")
     CommonResult deleteRedisStock(@RequestParam("ids") List<Long> ids);


    /**
     * 更新秒杀的信息
     * @param mallSecondProperty
     * @return
     */
    @PostMapping("/updateFlashInfo")
    CommonResult<MallSecondProperty> updateFlashInfo(@RequestBody  MallSecondProperty mallSecondProperty );
}
