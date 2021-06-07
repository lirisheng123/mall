package com.springboot.cloud.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/3/24 11:30
 * @Version 1.0
 */
@Data
public class UpdateLockedParam {
    List<Long> userId;
    Long statusLocked;
}
