package com.develop.bank.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        Enumeration<String> headerNames = httpRequest.getHeaderNames();

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String currentHeader = headerNames.nextElement();
                System.out.println("Header: " + currentHeader + " : " + httpRequest.getHeader(currentHeader ));
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
