package com.develop.bank.filters;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public class MyFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("HERE I HAVE AUTH FILTER");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }

//    public MyFilter() {
//        super("/bank-api/**");
//    }
//
//    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
//        System.out.println("HERE I HAVE AUTH FILTER");
//        return null;
//    }
}
