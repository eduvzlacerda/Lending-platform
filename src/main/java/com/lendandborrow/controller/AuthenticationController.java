package com.lendandborrow.controller;

import com.lendandborrow.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.noContent;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/authentication")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService  userService;

    @GetMapping
    public ResponseEntity<Void> login(String email, String password) {

        if(userService.loginUser(email, password)){
            return noContent().build();
        }
         return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
