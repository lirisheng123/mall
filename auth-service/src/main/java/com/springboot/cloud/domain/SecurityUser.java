package com.springboot.cloud.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 登录用户信息
 * Created by springboot on 2020/6/19.
 */
@Data
public class SecurityUser implements UserDetails {

    /**
     * ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户状态
     */
    private Boolean enabled=true;

    /**
     * 用户状态
     */
    private Boolean locked=true;

    /**
     * 权限数据
     */
    private Collection<SimpleGrantedAuthority> authorities;

    public SecurityUser() {

    }

//    public SecurityUser(UserDTO userDTO) {
//        this.setId(userDTO.getId());
//        this.setUsername(userDTO.getUsername());
//        this.setPassword(userDTO.getPassword());
//        this.setEnabled(userDTO.getStatus() == 1);
//        if (userDTO.getRoles() != null) {
//            authorities = new ArrayList<>();
//            userDTO.getRoles().forEach(item -> authorities.add(new SimpleGrantedAuthority(item)));
//        }
//    }

    public SecurityUser(UserRoleParam userRoleParam) {

        this.setId(userRoleParam.getUserId());
        this.setUsername(userRoleParam.getLoginName());
        this.setPassword(userRoleParam.getPasswordMd5());
//        this.setEnabled(true);
        if(userRoleParam.getLockedFlag()==1){
            this.setLocked(false);
        }

        List<String> roleNames = userRoleParam.getRoleNames();
        if (roleNames != null) {
            authorities = new ArrayList<>();
            roleNames.forEach(item->authorities.add(new SimpleGrantedAuthority(item)));
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
