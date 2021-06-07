package com.springboot.cloud.util;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : lirisheng
 * @date : 2020/9/15
 **/
@Setter
@Getter
public class ResBean {
    Integer status;
    String  message;
    Object  error;

    private ResBean(int status, String message, Object error){
        this.status=status;
        this.message=message;
        this.error=error;
    }

    public static  ResBean  OK(String message){
        return OK(message,null);
    }

    public static  ResBean  OK(String message,Object error){
          ResBean resBean = new ResBean(500,message,error);
          return  resBean;
    }

    public static  ResBean  Error(String message){
        return Error(message,null);
    }

    public static  ResBean  Error(String message,Object error){
        ResBean resBean = new ResBean(404,message,error);
        return  resBean;
    }


}
