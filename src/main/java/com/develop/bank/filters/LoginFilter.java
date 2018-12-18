package com.develop.bank.filters;

import com.develop.bank.util.HeaderMapRequestWrapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HeaderMapRequestWrapper httpReq = new HeaderMapRequestWrapper((HttpServletRequest) servletRequest);

        HttpServletResponse httpRes = (HttpServletResponse)servletResponse;

//        try{
//            bla();
//        } catch (Exception e) {
//
//        }

        filterChain.doFilter(httpReq, httpRes);
    }

    private void bla() throws Exception {
        String jwt = Jwts.builder()
                .setSubject("users/TzMUocMF4p")
                .setExpiration(new Date(1300819380))
                .claim("name", "Robert Token Man")
                .claim("scope", "self groups/admins")
                .claim("admin", true)
                .signWith(
                        SignatureAlgorithm.HS256,
                        "secret".getBytes("UTF-8")
                )
                .compact();
        System.out.println("TOKEN " + jwt);


//        String jwt = <jwt passed in from above>
                Jws<Claims> claims = Jwts.parser()
                .setSigningKey("secret".getBytes("UTF-8"))
                .parseClaimsJws(jwt);
        String scope = (String) claims.getBody().get("scope");
        Object admin = claims.getBody().get("admin");
        System.out.println("DECODE " + admin + " " + scope);
    }

    public void destroy() {

    }
}
