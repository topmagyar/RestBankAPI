package com.develop.bank.config;

import com.develop.bank.filters.MyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@ComponentScan(basePackages = "com.develop.bank.filters")
public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/ {

//    @Autowired
//    @Qualifier("userDetailsService")
//    UserDetailsService userDetailsService;

//    @Autowired
//    @Qualifier("tokenAuthenicationManager")
//    TokenAuthenticationManager tokenAuthenticationManager;

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .anonymous().disable()
//                .authorizeRequests()
//                .antMatchers("/bank-api/*").authenticated()
//                .and()
//                .addFilterBefore(myFilter(), FilterSecurityInterceptor.class);
//    }
//
//
//    @Bean(name="authenticationManager")
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

//    @Bean(name = "authenticationFilter")
//    public AuthenticationFilter authenticationFilter() {
//        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
//        tokenAuthenticationManager.setUserDetailsService(userDetailsService);
//        authenticationFilter.setAuthenticationManager(tokenAuthenticationManager);
//        return authenticationFilter;
//    }

//    @Bean(name = "myFilter")
//    public MyFilter myFilter() {
//        MyFilter myFilter = new MyFilter();
//        return myFilter;
//    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
//        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("superadmin").password("superadmin").roles("SUPERADMIN");
//    }
}
