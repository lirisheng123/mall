package com.springboot.cloud.aop;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import com.springboot.cloud.dto.WebLog;
import com.springboot.cloud.entity.MallRecommend;
import com.springboot.cloud.service.MallRecommendService;
import com.springboot.cloud.util.UserInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: lirisheng
 * @Date: 2021/5/3 12:03
 * @Version 1.0
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class WebLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

    @Autowired
    MallRecommendService mallRecommendService;

    @Pointcut("execution(public * com.springboot.cloud.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
    }

    @AfterReturning(value = "webLog()", returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //记录请求信息
        WebLog webLog = new WebLog();
        Object result = joinPoint.proceed();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            webLog.setDescription(apiOperation.value());
        }
        long endTime = System.currentTimeMillis();
        String urlStr = request.getRequestURL().toString();
        webLog.setBasePath(StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()));
        webLog.setIp(request.getRemoteUser());
        webLog.setMethod(request.getMethod());
        webLog.setParameter(getParameter(method, joinPoint.getArgs()));
        webLog.setResult(result);
        webLog.setSpendTime((int) (endTime - startTime));
        webLog.setStartTime(startTime);
        webLog.setUri(request.getRequestURI());
        webLog.setUrl(request.getRequestURL().toString());
        LOGGER.debug("{}", JSONUtil.parse(webLog));

        if(Pattern.matches("/product/updateInfo/\\d*",webLog.getUri())){

            log.debug("enter /product/updateInfo");

            String[] uri =  webLog.getUri().trim().split("/");
            Long goodId = Long.valueOf(uri[3]);
            Long userId = UserInfo.getUserIdByToken(request);
            if(userId!=0){
                MallRecommend mallRecommend =  mallRecommendService.selectByUserIdAndGoodId(userId,goodId);
                if(mallRecommend==null){
                    log.debug("enter create");
                    MallRecommend mallRecommend1 = new MallRecommend();
                    mallRecommend1.setGoodId(goodId);
                    mallRecommend1.setPreference(1);
                    mallRecommend1.setUserId(userId);
                    mallRecommendService.create(mallRecommend1);
                }else{
                    //更新
                    log.debug("enter update");
                    mallRecommend.setPreference(mallRecommend.getPreference()+1);
                    mallRecommendService.update(mallRecommend.getId(),mallRecommend);
                }
            }
        }

        return result;
    }

    /**
     * 根据方法和传入的参数获取请求参数
     */
    private Object getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.size() == 0) {
            return null;
        } else if (argList.size() == 1) {
            return argList.get(0);
        } else {
            return argList;
        }
    }

    public static void main(String [] arg){
        AntPathMatcher matcher  = new AntPathMatcher();
        System.out.println(matcher.match("/product/updateInfo/","/product/updateInfo/3"));

        System.out.println(Pattern.matches("/product/updateInfo/\\d*","/product/updateInfo/3"));

        String[] uri = "/product/updateInfo/15".trim().split("/");
        Long goodId = Long.valueOf(uri[3]);
        System.out.println("goodId:"+goodId);

    }
}