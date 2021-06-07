package com.springboot.cloud.service;


import com.springboot.cloud.api.CommonResult;
import com.springboot.cloud.domain.MallUser;
import com.springboot.cloud.domain.UserRoleParam;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:02
 * @Version 1.0
 */
//@Component
public class MallUserServiceImpl implements MallUserService {

    @Override
    public CommonResult<UserRoleParam> selectRolesByUsername(String username) {
        return null;
    }

    @Override
    public CommonResult registerUser(MallUser mallUser) {
        return null;
    }
}
