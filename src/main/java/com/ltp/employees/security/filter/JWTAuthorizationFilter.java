package com.ltp.employees.security.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ltp.employees.security.SecurityConstants;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    
    //Authorization: Bearer JWT
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization"); //Bearer JWT

        if (header == null || !header.startsWith(SecurityConstants.BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.replace(SecurityConstants.BEARER, ""); //JWT
        String user = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET_KEY))
            .build()
            .verify(token)
            .getSubject();   //returns the username of the user

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList());  //Because we do not store sensitive data in a token
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
