package com.example.mavbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    private UserAuthenticationProvider userAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .authenticationEntryPoint(userAuthenticationEntryPoint)
                .and()
                .addFilterBefore(new UserAuthenticationFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), UserAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
//                .antMatchers("/auth/signUp").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/signIn", "/auth/signUp").permitAll()
                .antMatchers(HttpMethod.GET, "/auth/roles").permitAll()
                .anyRequest().authenticated();
    }

   /* //por si acaso.
    @Bean
    public FilterRegistrationBean<UserAuthorizationFilter> userAuthorizationFilter(){
        var filterRegistrationBean = new FilterRegistrationBean<UserAuthorizationFilter>();
        filterRegistrationBean.setFilter(new UserAuthorizationFilter());
        filterRegistrationBean.addUrlPatterns("/api/person/*");
        filterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE -1);
        return filterRegistrationBean;
    }*/


    @Autowired
    public void setUserAuthenticationEntryPoint(UserAuthenticationEntryPoint userAuthenticationEntryPoint) {
        this.userAuthenticationEntryPoint = userAuthenticationEntryPoint;
    }

    @Autowired
    public void setUserAuthenticationProvider(UserAuthenticationProvider userAuthenticationProvider) {
        this.userAuthenticationProvider = userAuthenticationProvider;
    }
}
