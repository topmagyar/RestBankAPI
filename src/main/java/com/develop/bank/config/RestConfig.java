package com.develop.bank.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.develop.bank")
public class RestConfig extends WebMvcConfigurerAdapter {

}
