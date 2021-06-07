package com.springboot.cloud.common.dto.user;



import com.springboot.cloud.common.entity.user.MallUser;
import lombok.Data;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 20:39
 * @Version 1.0
 */
@Data
public class UserRoleParam extends MallUser {

    List<Long> roleIds;
    List<String> roleNames;
}
