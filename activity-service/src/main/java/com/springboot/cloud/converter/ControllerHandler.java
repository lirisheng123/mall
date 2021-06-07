package com.springboot.cloud.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @Author: lirisheng
 * @Date: 2021/3/29 22:19
 * @Version 1.0
 */
@ControllerAdvice
public class ControllerHandler {

    private Logger logger = LoggerFactory.getLogger(ControllerHandler.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 方法1，注册converter
        GenericConversionService genericConversionService = (GenericConversionService) binder.getConversionService();
        if (genericConversionService != null) {
            genericConversionService.addConverter(new DateConverter());
        }

        // 方法2，定义单格式的日期转换，可以通过替换格式，定义多个dateEditor，代码不够简洁
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        CustomDateEditor dateEditor = new CustomDateEditor(df, true);
//        binder.registerCustomEditor(Date.class, dateEditor);


        // 方法3，同样注册converter
//        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
//            @Override
//            public void setAsText(String text) throws IllegalArgumentException {
//                setValue(new DateConverter().convert(text));
//            }
//        });

    }
}
