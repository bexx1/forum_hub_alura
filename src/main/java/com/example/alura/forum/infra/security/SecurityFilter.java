package com.example.alura.forum.infra.security;

import com.example.alura.forum.infra.security.token.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// A filter is called before the controllers, so you can intercept the api requests and execute something or block certain requests
@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = getToken(request);
        var user = tokenService.getSubject(tokenJWT);

        filterChain.doFilter(request, response); // this code is necessary to call the next filters in the application
    }

    private String getToken(HttpServletRequest request) {
        // the token must be sent in a header authorization by the user that is making an api request
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null) {
            throw new RuntimeException("The jwt token was not sent in the Authorization header");
        }

        return authorizationHeader.replace("Bearer ", "");
    }
}
