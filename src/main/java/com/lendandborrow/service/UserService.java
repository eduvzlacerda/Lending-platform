package com.lendandborrow.service;

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

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //TODO: method never used
    public void setUserRole(EnumRole enumRole, long userId) {

        Role role = roleRepository.findByName(enumRole);

        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("user not found"));

        user.getRoles().add(role);

        userRepository.save(user);
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    //TODO USE PROPER SPRING AUTHETICATION
    public boolean loginUser(String email, String password) {
        if (email != null && password != null) {
            User user = userRepository.findByEmail(email);
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

    //TODO: understand the diference between get and find by id
    // --> getById creates a proxy which may or not exist in the db, where as the findById actually checks in the
    //db for its existence
    // get bz id can be convinient if you want to pass the entity instead of the id on a find query.
    // if you only use a get by id and save it like that if the entity doesnt exist it will throw a PLSQLException
    //saying it violates the relationship of article and user
    public User getUser(long userId) {
        return userRepository.getById(userId);
    }
}
