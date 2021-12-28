package com.lendandborrow.service;

import com.lendandborrow.ExcepetionHandling.exceptions.UserServiceException;
import com.lendandborrow.model.Role;
import com.lendandborrow.model.User;
import com.lendandborrow.model.dto.UserDTO;
import com.lendandborrow.model.enums.EnumRole;
import com.lendandborrow.repositories.RoleRepository;
import com.lendandborrow.repositories.UserRepository;
import com.lendandborrow.utils.converters.UserConverter;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //TODO: method never used
    public void setUserRole(EnumRole enumRole, UUID userId) throws RuntimeException {

        Role role = roleRepository.findByName(enumRole.toString()).orElseThrow(()->new UserServiceException("Role not found"));

        User user = userRepository.findById(userId).orElseThrow(() -> new UserServiceException("userId not found"));

        user.getRoles().add(role);


        userRepository.save(user);
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setId(UUID.randomUUID());
        return userRepository.save(user);
    }

    public boolean loginUser(String email, String password) {
        if (email != null && password != null) {
            User user = userRepository.findByEmail(email).orElseThrow(()-> new UserServiceException("Email does not exist"));
            if (user != null) {
                return passwordEncoder.matches(password, user.getPassword());
            }
        }
        return false;
    }

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserConverter::convertUserToUserDTO)
                .collect(Collectors.toList());
    }


    public User getUser(UUID userId) throws RuntimeException {
        return userRepository.findById(userId).orElseThrow(()-> new UserServiceException("userId not found"));
    }

}
