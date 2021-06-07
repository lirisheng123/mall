package com.springboot.cloud.dto;

import com.springboot.cloud.entity.MallFlashPromotionSession;
import com.springboot.cloud.entity.MallSecondProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/4/25 15:57
 * @Version 1.0
 */
@Data
public class SecondParams  extends MallFlashPromotionSession {

    List<MallSecondProperty> mallSecondProperties;
}
