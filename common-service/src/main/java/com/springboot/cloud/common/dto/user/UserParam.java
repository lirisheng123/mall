package com.springboot.cloud.common.dto.user;

import lombok.Data;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 21:30
 * @Version 1.0
 */
@Data
public class UserParam {

    private String loginName;

    private String phoneNumber;

    private Byte isDeleted;

    private Byte lockedFlag;



}
