package com.springboot.cloud.service.userService;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:25
 * @Version 1.0
 */
@FeignClient(contextId ="UserAddressService",value = "user-service")
@RequestMapping("/userAddress")
public interface UserAddressService {

}
