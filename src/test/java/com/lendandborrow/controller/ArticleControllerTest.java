package com.lendandborrow.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lendandborrow.model.User;
import com.lendandborrow.model.dto.ArticleDTO;
import com.lendandborrow.model.enums.EnumArticleStatus;
import com.lendandborrow.repositories.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ArticleController.class)
public class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private ArticleController articleController;

    @MockBean
    private UserRepository userRepository;


    @Test
    void getArticles() throws Exception {

        List<ArticleDTO> articleList = new ArrayList<>();

        Stream
                .of("1", "2", "3")
                .forEach(nr -> articleList.add(
                        ArticleDTO.builder()
                                .title(nr)
                                .description(nr)
                                .articleStatus(EnumArticleStatus.AVAILABLE)
                                .build()));

        given(articleController.getArticles()).willReturn(ok(articleList));

        mockMvc
                .perform(
                        get("/articles")
                        .contentType("application/json")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void searchArticlesEmpty() throws Exception {

        List<ArticleDTO> articleList = new ArrayList<>();

        Stream
                .of("1", "2", "3")
                .forEach(nr -> articleList.add(
                        ArticleDTO.builder()
                                .title(nr)
                                .description(nr)
                                .articleStatus(EnumArticleStatus.AVAILABLE)
                                .build()));

        Stream
                .of("1", "2", "3")
                .forEach(nr -> articleList.add(
                        ArticleDTO.builder()
                                .title(nr)
                                .description(nr)
                                .articleStatus(EnumArticleStatus.HIDDEN)
                                .build()));

        given(articleController.searchArticles(null)).willReturn(ok(articleList));
        mockMvc.perform(
                        get("/articles/search")
                                .contentType("application/json")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(6)));

    }

    @Test
    @Disabled
    void searchArticles() throws Exception {
        List<ArticleDTO> articleList = new ArrayList<>();

        Stream
                .of("1", "2", "3")
                .forEach(nr -> articleList.add(
                        ArticleDTO.builder()
                                .title(nr)
                                .description(nr)
                                .articleStatus(EnumArticleStatus.AVAILABLE)
                                .build()));

        Stream
                .of("1", "2", "3")
                .forEach(nr -> articleList.add(
                        ArticleDTO.builder()
                                .title(nr)
                                .description(nr)
                                .articleStatus(EnumArticleStatus.HIDDEN)
                                .build()));

        given(articleController.searchArticles("2")).willReturn(ok(articleList));
        mockMvc.perform(
                        get("/articles/search")
                                .param("searchString", "2")
                                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @Disabled
    void deleteArticle() throws Exception {

        mockMvc
                .perform(
                        delete("/articles/{id}", "1")
                        .contentType("application/json")
                )
                .andExpect(status().isOk());


    }

    // TODO: This test is not working!
    @Test
    @Disabled
    void addArticle() throws Exception {

        String articleAsJson =
                "{\n" +
                "  \"articleStatus\": \"AVAILABLE\",\n" +
                "  \"description\": \"Some sample description\",\n" +
                "  \"title\": \"The title of the article\"\n" +
                "}";

        User user = User
                .builder()
                .name("Max")
                .email("Max@Musterman.com")
                .build();

        //new User(1L, "Max", "Max@Musterman.com");

        when(userRepository.findById(user.getId())).thenReturn(java.util.Optional.of(user));

        mockMvc
                .perform(
                        post("/articles/{userId}", "1")
                        .contentType("application/json")
                        .content(articleAsJson)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.articlesStatus", is("AVAILABLE")))
                .andExpect(jsonPath("$.description", is("Some sample description")))
                .andExpect(jsonPath("$.title", is("The title of the article")));

    }

}
