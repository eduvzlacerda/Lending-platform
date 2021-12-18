package com.lendandborrow.controller;

import com.lendandborrow.CommonIntegrationTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ArticleControllerTest extends CommonIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(scripts = "classpath:/integration.sql")
    void getArticle() throws Exception {

        mockMvc.perform(
                        get("/articles")
                                .contentType("application/json")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].title").value("Article1")); // TODO: access first entry of array maybe?
    }


}