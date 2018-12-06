package com.develop.bank.filters;

import com.develop.bank.util.HeaderMapRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public class RegisterFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        HeaderMapRequestWrapper httpReq = new HeaderMapRequestWrapper((HttpServletRequest) servletRequest);
        httpReq.addHeader("password",httpRequest.getHeader("password") + "228");
        HttpServletResponse httpRes = (HttpServletResponse)servletResponse;

        filterChain.doFilter(httpReq, httpRes);
    }

    public void destroy() {

    }
}
