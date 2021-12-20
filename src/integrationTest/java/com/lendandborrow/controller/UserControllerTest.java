package com.lendandborrow.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lendandborrow.CommonIntegrationTest;
import com.lendandborrow.model.User;
import com.lendandborrow.model.dto.UserDTO;
import com.lendandborrow.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserControllerTest extends CommonIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void addUser() throws Exception {

        UserDTO user = UserDTO.builder()
                .name("Test")
                .email("test@mail.com")
                .password("My Password")
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

    @Test
    @Sql(scripts = "classpath:/integration.sql")
    void getUsers() throws Exception {

        mockMvc.perform(
                        get("/users")
                                .contentType("application/json")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }



}
