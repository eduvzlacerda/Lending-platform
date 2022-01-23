package com.lendandborrow.controller;

import com.lendandborrow.model.dto.UserDTO;
import com.lendandborrow.repositories.UserRepository;
import com.lendandborrow.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/authentication")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService  userService;

    @PostMapping
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {

        if(userService.loginUser(userDTO.getEmail(), userDTO.getPassword())) {

            return ok(userService.findByEmail(userDTO.getEmail()));

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

}