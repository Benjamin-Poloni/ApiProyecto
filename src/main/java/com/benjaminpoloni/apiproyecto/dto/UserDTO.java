package com.benjaminpoloni.apiproyecto.dto;

import com.benjaminpoloni.apiproyecto.models.RoleEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private Set<String> roles;



}
