package com.benjaminpoloni.apiproyecto.security;


import com.benjaminpoloni.apiproyecto.models.UserEntity;
import com.benjaminpoloni.apiproyecto.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MigrationAuthenticationProvider extends DaoAuthenticationProvider {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public MigrationAuthenticationProvider(UserDetailsService userDetailsService,
                                           PasswordEncoder passwordEncoder,
                                           UserRepository userRepository) {
        super();
        this.setUserDetailsService(userDetailsService);
        this.setPasswordEncoder(passwordEncoder);

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        super.additionalAuthenticationChecks(userDetails, authentication);

        if (userDetails.getPassword() != null && !userDetails.getPassword().startsWith("$2a$")) {

            String presentedPassword = authentication.getCredentials().toString();
            if(presentedPassword.equals(userDetails.getPassword())){
                String newEncondedPassword = passwordEncoder.encode(presentedPassword);

                UserEntity userEntity = (UserEntity) userDetails;
                userEntity.setPassword(newEncondedPassword);
                userRepository.save(userEntity);

                System.out.println("contrasenia actualizada con exito");
            }

        }
    }
}
