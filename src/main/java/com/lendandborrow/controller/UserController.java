package com.lendandborrow.controller;

import com.lendandborrow.model.User;
import com.lendandborrow.repositories.UserRepository;
import com.lendandborrow.service.UserDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

    private final UserRepository userRepository;

    private final UserDataService userDataService;


    public UserController(UserRepository userRepository, UserDataService userDataService) {
        this.userRepository = userRepository;
        this.userDataService = userDataService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/users")
    void addUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public boolean register(String name, String email, String password) {
        return userDataService.registerUser(name, email, password);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public boolean login(String email, String password) {
        return userDataService.loginUser(email, password);
    }

}
