package com.springboot.cloud.exception;

import com.springboot.cloud.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局处理Oauth2抛出的异常
 * Created by springboot on 2020/7/17.
 */
@ControllerAdvice
@Slf4j
public class Oauth2ExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = OAuth2Exception.class)
    public CommonResult handleOauth2(OAuth2Exception e) {

        return CommonResult.failed(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult handleException(Exception e) {

        return CommonResult.failed(e.getMessage());
    }
}
