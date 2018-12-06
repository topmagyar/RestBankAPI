package com.develop.bank.filters;

import javax.servlet.*;
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

}
