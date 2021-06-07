package com.springboot.cloud.service;


import com.springboot.cloud.api.CommonResult;
import com.springboot.cloud.dto.MallRoleNameIntefaceName;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:04
 * @Version 1.0
 */
//@Component
public class RoleServiceImpl implements RoleService {
    @Override
    public CommonResult<List<MallRoleNameIntefaceName>> selectAllRoleAndInteName() {
        return null;
    }
}
