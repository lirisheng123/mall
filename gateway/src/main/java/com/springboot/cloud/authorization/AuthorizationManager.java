package com.springboot.cloud.authorization;

import cn.hutool.core.convert.Convert;
import com.springboot.cloud.api.PathMatcherUtil;
import com.springboot.cloud.constant.AuthConstant;
import com.springboot.cloud.constant.RedisConstant;
import com.springboot.cloud.dto.MallRoleNameIntefaceName;
import com.springboot.cloud.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 鉴权管理器，用于判断是否有资源的访问权限
 * Created by macro on 2020/6/19.
 */
@Component
@Slf4j
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
//        @Qualifier("roleService")
    RoleService roleService;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        //从Redis中获取当前路径可访问角色列表
        URI uri = authorizationContext.getExchange().getRequest().getURI();
//        Object obj = redisTemplate.opsForHash().get(RedisConstant.RESOURCE_ROLES_MAP, uri.getPath());
//        List<String> authorities = Convert.toList(String.class,obj);
        log.info("uri.path:"+uri.getPath());
        List<MallRoleNameIntefaceName> mall = roleService.selectAllRoleAndInteName().getData();
        log.info("mall:"+mall);
        List<String> authorities = PathMatcherUtil.matchesGetRole(mall,uri.getPath());
        log.info("authoritiesBefore:"+authorities);
        authorities = authorities.stream().map(i -> i = AuthConstant.AUTHORITY_PREFIX + i).collect(Collectors.toList());
        log.info("authoritiesAfter:"+authorities);
        //认证通过且角色匹配的用户可访问当前路径
        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authorities::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

}
