package com.develop.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@Configuration
@ComponentScan("com.develop.bank.filters")
public class WebConfig2 extends WebMvcConfigurerAdapter {

    @Autowired
    HandlerInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor)
                .addPathPatterns("**/bank-api/bla**");
    }
}
