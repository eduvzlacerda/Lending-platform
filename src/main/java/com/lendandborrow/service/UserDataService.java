package com.lendandborrow.service;

import com.lendandborrow.model.Role;
import com.lendandborrow.model.User;
import com.lendandborrow.model.enums.EnumRole;
import com.lendandborrow.repositories.RoleRepository;
import com.lendandborrow.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDataService {

    final RoleRepository roleRepository;

    final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserDataService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void setUserRole(EnumRole enumRole, Long userId) {

        Role role = roleRepository.findRoleByName(enumRole.toString());

        User user = userRepository.findById(userId).orElseThrow();

        user.getRoles().add(role);

        userRepository.save(user);

    }

    public boolean registerUser(String name, String email, String password) {
        if(name != null && email != null && password != null) {
            User user = new User(name, email);
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean loginUser(String email, String password) {
        if(email != null && password != null) {
            User user = userRepository.findByEmail(email);
            if (user != null) {
                if (passwordEncoder.matches(password, user.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }

}
