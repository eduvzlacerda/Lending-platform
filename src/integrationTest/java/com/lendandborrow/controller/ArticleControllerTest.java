package com.lendandborrow.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.lendandborrow.CommonIntegrationTest;
import com.lendandborrow.model.Article;
import com.lendandborrow.model.dto.ArticleDTO;
import com.lendandborrow.model.enums.EnumArticleStatus;
import com.lendandborrow.repositories.ArticleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ArticleControllerTest extends CommonIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @Sql(scripts = "classpath:/integration.sql")
    void getArticles() throws Exception {

        mockMvc.perform(
                        get("/articles")
                                .contentType("application/json")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].title").value("Article1")); // TODO: access first entry of array maybe?
    }

    @Test
    @Sql(scripts = "classpath:/integration.sql")
    void addArticle() throws Exception {

        ArticleDTO article = ArticleDTO.builder()
                .title("Title")
                .description("Description")
                .articleStatus(EnumArticleStatus.AVAILABLE)
                .userId(UUID.fromString("afe19162-047b-473a-9552-0c254cac753a"))
                .build();

        String articleAsString = objectMapper.writeValueAsString(article);

        MvcResult mvcResult = mockMvc.perform(
                        post("/articles")
                                .contentType("application/json")
                                .content(articleAsString)
                )
                .andExpect(status().isOk())
                .andReturn();

        String id = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.id");

        Optional<Article> optionalArticle = articleRepository.findById(UUID.fromString(id));

        optionalArticle
                .ifPresentOrElse(

                        presentArticle -> {

                            Assertions.assertEquals("Title", presentArticle.getTitle());
                            Assertions.assertEquals("Description", presentArticle.getDescription());
                            Assertions.assertEquals(EnumArticleStatus.AVAILABLE, presentArticle.getArticleStatus());

                            },

                        () -> Assertions.fail("Article not found"));

    }


}