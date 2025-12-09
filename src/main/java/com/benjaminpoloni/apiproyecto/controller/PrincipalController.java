package com.benjaminpoloni.apiproyecto.controller;


import com.benjaminpoloni.apiproyecto.dto.UserDTO;
import com.benjaminpoloni.apiproyecto.models.Erole;
import com.benjaminpoloni.apiproyecto.models.RoleEntity;
import com.benjaminpoloni.apiproyecto.models.UserEntity;
import com.benjaminpoloni.apiproyecto.repository.RoleRepository;
import com.benjaminpoloni.apiproyecto.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class PrincipalController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @GetMapping("/listarUsers")
    public ResponseEntity<List<UserDTO>> listarUsers() {
        try {
            List<UserEntity> users = userRepository.findAll();

            List<UserDTO> usersDto = users.stream().map(user -> new UserDTO(
                    user.getEmail(),
                    user.getUsername(),
                    null,
                    user.getRoles().stream().map(
                            role -> role.getName().name()).collect(Collectors.toSet())
                    )
            ).toList();

            return ResponseEntity.ok(usersDto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {

        Set<RoleEntity> roles = userDTO.getRoles().stream()
                .map(roleString -> {
                    Erole erole = Erole.valueOf(roleString);  // convierte String → Enum
                    return roleRepository.findByName(erole)
                            .orElseThrow(() -> new RuntimeException("Role not found: " + erole));
                })
                .collect(Collectors.toSet());

        UserEntity user = UserEntity.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .roles(roles)
                .build();

        userRepository.save(user);

        return ResponseEntity.ok(user);
    }



    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String id) {

        userRepository.deleteById(Long.parseLong(id));
        return "User deleted".concat(id);
    }
}
