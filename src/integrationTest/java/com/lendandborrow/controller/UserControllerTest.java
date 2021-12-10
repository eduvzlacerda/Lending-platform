package com.lendandborrow.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lendandborrow.model.Role;
import com.lendandborrow.model.User;
import com.lendandborrow.model.dto.UserDTO;
import com.lendandborrow.model.enums.EnumRole;
import com.lendandborrow.repositories.RoleRepository;
import com.lendandborrow.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:/integration.sql")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void addUser() throws Exception {

        Role adminRole = roleRepository.findByName(EnumRole.ADMIN);

        UserDTO user = UserDTO.builder()
                .name("Test")
                .email("test@mail.com")
                .password("My Password")
                .roles(Collections.singleton(adminRole))
                .build();

        String userAsString = objectMapper.writeValueAsString(user);

        mockMvc.perform(
                post("/users")
                        .contentType("application/json")
                        .content(userAsString)
                )
                .andExpect(status().isOk());

        User userFound = userRepository.findByName("Test");

        Assertions.assertEquals("test@mail.com", userFound.getEmail());

    }



}
