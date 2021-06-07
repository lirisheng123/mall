package com.springboot.cloud.util;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.nimbusds.jose.JWSObject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lirisheng
 * @Date: 2021/5/5 15:35
 * @Version 1.0
 */
@Slf4j
public class UserInfo {

    public  static Long getUserIdByToken(HttpServletRequest request){
        String token= request.getHeader("authorizationtest");

        if(token==null){
            log.debug("没有token");
            return 0L;
        }

        //从token中解析用户信息并设置到Header中去
//        log.debug("token:"+token);
        try {
            //从token中解析用户信息并设置到Header中去
            String realToken = token.replace("Bearer ", "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            String userStr = jwsObject.getPayload().toString();
            Gson gson = new Gson();
            Map<String, Object> rtMap = gson.fromJson(userStr,new TypeToken<Map<String,Object>>() {}.getType());
            Integer  userId = ((Double)rtMap.get("id")) .intValue();
            log.debug("useriID:"+userId);
            return userId.longValue();
        } catch (Exception parse) {
            log.debug("token解析失败");
            return 0L;
//            e.printStackTrace();
        }
    }
}
