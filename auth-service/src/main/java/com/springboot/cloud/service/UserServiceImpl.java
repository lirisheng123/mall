package com.springboot.cloud.service;


import com.springboot.cloud.constant.MessageConstant;
import com.springboot.cloud.domain.SecurityUser;
import com.springboot.cloud.domain.UserDTO;
import com.springboot.cloud.domain.UserRoleParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户管理业务类
 * Created by springboot on 2020/6/19.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserDetailsService {

    private List<UserDTO> userList;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
//    @Qualifier("mallUserService")
    MallUserService mallUserService;

//    @PostConstruct
//    public void initData() {
//        String password = passwordEncoder.encode("123456");
//        userList = new ArrayList<>();
//        userList.add(new UserDTO(1L,"springboot", password,1, CollUtil.toList("ADMIN")));
//        userList.add(new UserDTO(2L,"andy", password,1, CollUtil.toList("TEST")));
//    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        List<UserDTO> findUserList = userList.stream().filter(item -> item.getUsername().equals(username)).collect(Collectors.toList());
//        if (CollUtil.isEmpty(findUserList)) {
//            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
//        }
//        SecurityUser securityUser = new SecurityUser(findUserList.get(0));
//        if (!securityUser.isEnabled()) {
//            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
//        } else if (!securityUser.isAccountNonLocked()) {
//            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
//        } else if (!securityUser.isAccountNonExpired()) {
//            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
//        } else if (!securityUser.isCredentialsNonExpired()) {
//            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
//        }
//        return securityUser;
//    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //根据username查找user+inteface
        UserRoleParam userRoleParam =  mallUserService.selectRolesByUsername(s).getData();
        log.debug("userRoleParam:"+userRoleParam);
        //转化为SecurityUser
        //判断是否合法
        //返回
        if (userRoleParam==null) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }

        SecurityUser securityUser = new SecurityUser(userRoleParam);
        log.debug("securityUser:"+securityUser);
        if (!securityUser.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
//            throw  new UsernameNotFoundException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUser;
    }
}
