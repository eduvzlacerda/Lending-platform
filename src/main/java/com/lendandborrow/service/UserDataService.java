package com.lendandborrow.service;

import com.lendandborrow.model.Role;
import com.lendandborrow.model.User;
import com.lendandborrow.model.enums.EnumRole;
import com.lendandborrow.repositories.RoleRepository;
import com.lendandborrow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDataService {

    final
    RoleRepository roleRepository;

    final
    UserRepository userRepository;

    public UserDataService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public void setUserRole(EnumRole enumRole, Long userId) {

        Role role = roleRepository.findRoleByName(enumRole.toString());

        User user = userRepository.findById(userId).orElseThrow();

        user.getRoles().add(role);

        userRepository.save(user);

    }



}
