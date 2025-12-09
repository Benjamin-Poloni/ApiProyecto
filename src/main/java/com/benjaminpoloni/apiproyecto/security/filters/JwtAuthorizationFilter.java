package com.benjaminpoloni.apiproyecto.security.filters;

import com.benjaminpoloni.apiproyecto.security.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {


    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    public JwtAuthorizationFilter(final JwtUtils jwtUtils, final UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        String tokenHeader = request.getHeader("Authorization");


        if (tokenHeader != null ) {
            String cleanHeader = tokenHeader.trim();

            if (cleanHeader.startsWith("Bearer ")) {
                String token = cleanHeader.replace("Bearer ", "");

                System.out.println("DEBUG TOKEN FINAL LIMPIO (replace): [" + token + "]");

                if (jwtUtils.tokenValid(token)) {

                String username = jwtUtils.getSubject(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());


                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            }
        }

//        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
//            int tokenStartIndex = tokenHeader.indexOf("Bearer ") + 7;
//
//            String token = tokenHeader.substring(tokenStartIndex);
//            String correctToken = token.trim();
//
//            System.out.println("DEBUG TOKEN RECIBIDO: [" + token + "]");
//            System.out.println("DEBUG TOKEN CORREGIDO: [" + correctToken + "]");
//
//
//            if (jwtUtils.tokenValid(correctToken)) {
//
//                String username = jwtUtils.getSubject(correctToken);
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
//
//
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
        filterChain.doFilter(request, response);

    }
}
