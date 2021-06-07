package com.springboot.cloud.util;

/**
 * 封装API的错误码
 * Created by springboot on 2019/4/19.
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
