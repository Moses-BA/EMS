package com.ltp.employees.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.ltp.employees.security.filter.AuthenticationFilter;
import com.ltp.employees.security.filter.ExceptionHandlerFilter;
import com.ltp.employees.security.filter.JWTAuthorizationFilter;
import com.ltp.employees.security.manager.CustomAuthenticationManager;

import lombok.AllArgsConstructor;

import org.springframework.security.config.Customizer;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private CustomAuthenticationManager customAuthManager;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationFilter authFilter = new AuthenticationFilter(customAuthManager);
        authFilter.setFilterProcessesUrl("/authenticate");

        http
            .headers(headers -> headers.frameOptions().disable())
            .csrf(csrf -> csrf.disable())
            .authorizeRequests(requests -> requests
                    .antMatchers("/h2/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/user/register").permitAll()
                    .antMatchers(HttpMethod.POST, "/user/registeradmin").permitAll()
                    .antMatchers("/**").permitAll() 
                    .anyRequest().authenticated()
                    .and()
                    .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
                    .addFilter(authFilter)
                    .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class))
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
