package com.springboot.cloud.controller.userController;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:25
 * @Version 1.0
 */
@Controller
@ResponseBody
@Api(tags = "UserAddressController", description = "用户地址管理")
@RequestMapping("/admin/userAddress")
public class UserAddressController {

}
