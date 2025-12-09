package com.benjaminpoloni.apiproyecto.service;


import com.benjaminpoloni.apiproyecto.dto.UserDTO;
import com.benjaminpoloni.apiproyecto.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

   private   final UserRepository userRepository;

}
