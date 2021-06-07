package com.springboot.cloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.cloud.dao.MallCompanyAddressMapper;
import com.springboot.cloud.entity.MallCompanyAddress;
import com.springboot.cloud.service.MallCompanyAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: lirisheng
 * @Date: 2021/2/23 11:41
 * @Version 1.0
 */
@Service
public class MallCompanyAddressServiceImpl implements MallCompanyAddressService {

    @Autowired
    MallCompanyAddressMapper  mapper;
    @Override
    public int insert(MallCompanyAddress mallCompanyAddress) {
        return  mapper.insert(mallCompanyAddress) ;
    }

    @Override
    public int update(Long id,MallCompanyAddress mallCompanyAddress) {
        mallCompanyAddress.setId(id);
        return  mapper.updateByPrimaryKey(mallCompanyAddress);
    }

    @Override
    public List<MallCompanyAddress> selectList(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        return mapper.selectAll() ;
    }

    @Override
    public MallCompanyAddress select(Long id) {
        return  mapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int UpdateDefault(Long id, Integer type) {
        int count=0;
        if(type==0){
            //判断是否有send 的默认
             MallCompanyAddress defaultId= mapper.judgeSendDefault();
            if(defaultId!=null){
                //有,设置为不是默认
                mapper.changSendStatus(defaultId.getId(),0);
            }
            mapper.changSendStatus(id,1);

        }else if(type==1){
            //判断是否有receive的默认
            MallCompanyAddress defaultId= mapper.judgeReceiveDefault();
            if(defaultId!=null){
                //有,设置为不是默认
                mapper.changReceiveStatus(defaultId.getId(),0);
            }
            mapper.changReceiveStatus(id,1);
        }else{
            return 0;
        }
        return 1;
    }

    @Override
    public MallCompanyAddress selectByType(Integer type) {

        if(type==0){
            //获取send的默认状态
            return mapper.judgeSendDefault();
        }
        else if(type==1){
            //获取receive的默认状态
            return  mapper.judgeReceiveDefault();
        }else{
            return  null;
        }

    }

    @Override
    public List<MallCompanyAddress> selectAll() {
        return  mapper.selectAll();
    }
}
