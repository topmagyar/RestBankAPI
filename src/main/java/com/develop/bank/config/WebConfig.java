package com.develop.bank.config;

import com.develop.bank.filters.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@Configuration
@EnableWebMvc
//@ComponentsScan(basePackages = "com.develop.bank.controllers")
@ComponentScans(value = {@ComponentScan("com.develop.bank.controllers")})
public class WebConfig extends WebMvcConfigurerAdapter {

}
