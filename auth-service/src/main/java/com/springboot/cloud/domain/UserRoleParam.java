package com.springboot.cloud.domain;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:39
 * @Version 1.0
 */
@Data
@ToString(callSuper=true)
public class UserRoleParam extends MallUser {

    List<Long> roleIds;
    List<String> roleNames;
}
