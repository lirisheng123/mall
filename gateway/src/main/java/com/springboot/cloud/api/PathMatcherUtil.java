package com.springboot.cloud.api;

import com.springboot.cloud.dto.MallRoleNameIntefaceName;
import org.springframework.util.AntPathMatcher;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Author: lirisheng
 * @Date: 2021/3/16 20:10
 * @Version 1.0
 */
public class PathMatcherUtil {

    /**
     * 实际验证路径匹配权限
     *
     * @param matchPath 权限url
     * @param path      访问路径
     * @return 是否拥有权限
     */
    public static boolean match(String matchPath, String path) {
        SpringAntMatcher springAntMatcher = new SpringAntMatcher(matchPath, true);
        return springAntMatcher.matches(path);
    }

    /**
     * 实际验证路径匹配权限
     *
     * @param list 权限url
     * @param path 访问路径
     * @return 是否拥有权限
     */
    public static boolean matches(Collection<String> list, String path) {
        for (String s : list) {
            SpringAntMatcher springAntMatcher = new SpringAntMatcher(s, true);
            if (springAntMatcher.matches(path)) {
                return true;
            }
        }
        return false;
    }

    public static  List<String>  matchesGetRole(List<MallRoleNameIntefaceName>  mall,String path){

        List<String> roleNames = new ArrayList<>();
        Iterator iterator = mall.iterator();
        while(iterator.hasNext()){
            MallRoleNameIntefaceName roleName = (MallRoleNameIntefaceName)iterator.next();
            if(match(roleName.getIntefaceName(),path)){
                //匹配成功
                roleNames =  roleName.getRoleName();
                return  roleNames;
            }
        }
        return roleNames;

    }

    /**
     * 地址表达式匹配工具
     */
    private static class SpringAntMatcher implements Matcher {
        private final AntPathMatcher antMatcher;
        private final String pattern;

        private SpringAntMatcher(String pattern, boolean caseSensitive) {
            this.pattern = pattern;
            this.antMatcher = createMatcher(caseSensitive);
        }

        @Override
        public boolean matches(String path) {
            return this.antMatcher.match(this.pattern, path);
        }

        @Override
        public Map<String, String> extractUriTemplateVariables(String path) {
            return this.antMatcher.extractUriTemplateVariables(this.pattern, path);
        }

        private static AntPathMatcher createMatcher(boolean caseSensitive) {
            AntPathMatcher matcher = new AntPathMatcher();
            matcher.setTrimTokens(false);
            matcher.setCaseSensitive(caseSensitive);
            return matcher;
        }
    }

    private interface Matcher {
        boolean matches(String var1);

        Map<String, String> extractUriTemplateVariables(String var1);
    }

    public static void main(String arg[]){
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        System.out.println(antPathMatcher.match("/admin/**","/admin/grand/list"));

    }
}

