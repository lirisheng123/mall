package com.springboot.cloud.service;


import com.springboot.cloud.dto.MallGrandQueryParam;
import com.springboot.cloud.entity.MallGrand;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品品牌管理Service
 * Created by macro on 2018/4/26.
 */
public interface MallGrandService {
    /**
     * 获取所有品牌
     */
    List<MallGrand> listAllBrand(Integer pageSize,Integer pageNum);

    /**
     * 创建品牌
     */
    int createBrand(MallGrand mallGrand);

    /**
     * 修改品牌
     */
    @Transactional
    int updateBrand(Long grandId ,MallGrand mallGrand);

    /**
     * 删除品牌
     */
    int deleteBrand(Long id);

    /**
     * 批量删除品牌
     */
    int deleteBrand(List<Long> ids);

    /**
     * 分页查询品牌
     */
    List<MallGrand> listBrand(MallGrandQueryParam mallGrandQueryParam, int pageNum, int pageSize);

    /**
     * 获取品牌
     */
    MallGrand getBrand(Long id);

//    /**
//     * 修改显示状态
//     */
//    int updateShowStatus(List<Long> ids, Integer showStatus);
//
//    /**
//     * 修改厂家制造商状态
//     */
//    int updateFactoryStatus(List<Long> ids, Integer factoryStatus);
}
