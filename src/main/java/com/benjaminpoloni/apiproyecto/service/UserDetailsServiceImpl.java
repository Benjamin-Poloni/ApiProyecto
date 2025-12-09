package com.benjaminpoloni.apiproyecto.service;

import com.benjaminpoloni.apiproyecto.models.UserEntity;
import com.benjaminpoloni.apiproyecto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserDetailsServiceImpl  implements UserDetailsService {


    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        UserEntity userDetails =  userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("El usuario" + username +"no existe"));

        Collection<? extends GrantedAuthority> authorities = userDetails.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getName().name())))
                .collect(Collectors.toSet());

        return new User(userDetails.getUsername(),
                userDetails.getPassword(),
                true,
                true,
                true,
                true, authorities);
    }
}
