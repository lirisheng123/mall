package com.springboot.cloud.common.dto.user;


import com.springboot.cloud.common.entity.user.MallRole;
import lombok.Data;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/22 19:53
 * @Version 1.0
 */
@Data
public class MallRoleIntefaceParam extends MallRole {
    List<Long> mallInterfacenIds;
    List<String> mallInterfacenNames;
}
