package com.develop.bank.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public class MyWebAppInitializer /*extends AbstractAnnotationConfigDispatcherServletInitializer*/ {

//    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { WebConfig.class };
    }

//    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

//    @Override
    protected String[] getServletMappings() {
        return new String[] { "/bank-api/*" };
    }
}
