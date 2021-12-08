package com.lendandborrow.controller;

import com.lendandborrow.model.Article;
import com.lendandborrow.repositories.ArticleRepository;
import com.lendandborrow.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ArticleController {

    private final ArticleRepository articleRepository;

    private final UserService userService;

    public ArticleController(
            ArticleRepository articleRepository,
            UserService userService) {

        this.articleRepository = articleRepository;

        this.userService = userService;

    }

    @GetMapping("/articles")
    public List<Article> getArticles() {
        return (List<Article>) articleRepository.findAll();
    }

    @DeleteMapping("/articles")
    public void deleteArticle(@RequestParam Long articleId) {
        this.articleRepository.deleteById(articleId);
    }

    @PostMapping("/articles")
    void addArticle(@RequestBody Article article, @RequestParam Long userId) {

        this.userService.addArticle(article, userId);

    }



}
