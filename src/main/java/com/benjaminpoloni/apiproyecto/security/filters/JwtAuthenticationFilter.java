package com.benjaminpoloni.apiproyecto.security.filters;

import com.benjaminpoloni.apiproyecto.models.UserEntity;
import com.benjaminpoloni.apiproyecto.security.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private  JwtUtils jwtUtils;


    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        User user = (User) authResult.getPrincipal();

        String token = jwtUtils.generateAccesToken(user.getUsername());

        response.addHeader("Authorization", "Bearer " + token);


        Map<String, Object> httpResponse = new HashMap<String, Object>();

        httpResponse.put("token", token);
        httpResponse.put("Message", "Authentication successful");
        httpResponse.put("username", user.getUsername());


        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");

        new ObjectMapper().writeValue(response.getWriter(), httpResponse);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        UserEntity user = null;

        try {
            user = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
        }catch (IOException e){

        }

        String username = (user != null) ? user.getUsername() : "";
        String password = (user != null) ? user.getPassword() : "";

        UsernamePasswordAuthenticationToken authUser = new UsernamePasswordAuthenticationToken(username,password);
        return getAuthenticationManager().authenticate(authUser);
    }
}
