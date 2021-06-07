package com.springboot.cloud.service;

import com.springboot.cloud.entity.MallRoleInterface;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/21 22:35
 * @Version 1.0
 */
public interface MallRoleInterfaceService {

    /**
     * 根据角色id查询接口
     */
    List<String> selectInteNameByRoleId(Long roleId);





    /**
     * 修改角色Id的接口分配
     */
    int updateIntefaceByRoleId(Long roleId,List<Long> intefaceId);

    /**
     * 批量添加
     */
    int insertList(List<MallRoleInterface> mallRoleInterfaces);

    /**
     * 查找是否存在roleId的记录
     * @param roleId
     * @return
     */
    Long selectAnyByRoleId(Long roleId);

    /**
     * 删除角色Id的接口分配
     */
    int deleteIntefaceByRoleId(Long roleId);


    /**
     * 通过inte的id来查询角色名
     */
    List<String>  selectRoleNameByInteId(Long inteId);

}
