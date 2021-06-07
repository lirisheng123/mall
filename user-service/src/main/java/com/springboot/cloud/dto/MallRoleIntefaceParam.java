package com.springboot.cloud.dto;

import com.springboot.cloud.entity.MallRole;
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
//    Long mallInterfacenIds;
//    String mallInterfacenNames;
}
