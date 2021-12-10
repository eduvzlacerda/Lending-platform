package com.lendandborrow.controller;

import com.lendandborrow.model.User;
import com.lendandborrow.model.dto.UserDTO;
import com.lendandborrow.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lendandborrow.utils.converters.UserConverter.convertUserDTOToUser;
import static com.lendandborrow.utils.converters.UserConverter.convertUserToUserDTO;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ok(userService.findAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {

        User user = userService.registerUser(convertUserDTOToUser(userDTO));

        return ok(convertUserToUserDTO(user));
    }

}
