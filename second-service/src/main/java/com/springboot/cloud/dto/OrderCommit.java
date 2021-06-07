package com.springboot.cloud.dto;


import com.springboot.cloud.pojo.MallOrder;
import com.springboot.cloud.pojo.MallOrderItem;
import lombok.Data;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/3/27 16:43
 * @Version 1.0
 */
@Data
public class OrderCommit extends MallOrder {

    List<MallOrderItem> mallOrderItems;
    List<Long> shopCateIds;
    Long orderAddressId;
    Long couponHistoryId;
}
