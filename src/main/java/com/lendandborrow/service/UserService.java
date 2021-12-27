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
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import static com.lendandborrow.utils.converters.UserConverter.convertUserToUserDTO;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void setUserRole(EnumRole enumRole, long userId) {

        Role role = roleRepository.findByName(enumRole.toString()).orElseThrow(()->new RuntimeException("Role not found"));

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("userId not found"));

        if(user.getRoles() == null) {
            user.setRoles(new HashSet<>());
        }

        user.getRoles().add(role);

        userRepository.save(user);
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setId(UUID.randomUUID());
        User createdUser = userRepository.save(user);
        setUserRole(EnumRole.USER, createdUser.getId());
        return userRepository.save(user);
    }

    public boolean loginUser(String email, String password) {
        if (email != null && password != null) {
            User user = userRepository.findByEmail(email).orElseThrow(()-> new EntityNotFoundException("Email does not exist"));
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

    public UserDTO findByEmail(String email) {
        return convertUserToUserDTO(userRepository.findByEmail(email));
    }

    //TODO: understand the diference between get and find by id
    // --> getById creates a proxy which may or not exist in the db, where as the findById actually checks in the
    //db for its existence
    // get bz id can be convinient if you want to pass the entity instead of the id on a find query.
    // if you only use a get by id and save it like that if the entity doesnt exist it will throw a PLSQLException
    //saying it violates the relationship of article and user

    public User getUser(UUID userId) throws RuntimeException {
        return userRepository.findById(userId).orElseThrow(()-> new RuntimeException("userId not found"));
    }

}
