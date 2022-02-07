package com.lendandborrow.controller;

import com.lendandborrow.model.Article;
import com.lendandborrow.model.User;
import com.lendandborrow.model.dto.ArticleDTO;
import com.lendandborrow.service.ArticleService;
import com.lendandborrow.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.lendandborrow.utils.converters.ArticleConverter.convertArticleDTOToArticle;
import static com.lendandborrow.utils.converters.ArticleConverter.convertArticleToArticleDTO;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticle(@PathVariable UUID id) {
        return ok(articleService.findById(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<ArticleDTO>> getUserArticles(@PathVariable UUID id) {
        return ok(articleService.findArticlesOfUser(userService.getUser(id)));
    }

    public ResponseEntity<List<ArticleDTO>> getArticles() {

        return ok(articleService.findAllArticles());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ArticleDTO>> searchArticles(@RequestParam(required = false) String searchString) {
        return articleService.searchArticlesbyString(searchString);
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> addArticle(@RequestBody ArticleDTO articleDTO) {

        User user = userService.getUser(articleDTO.getUserId());
        if (user == null) {
            return new ResponseEntity<>(articleDTO, HttpStatus.NOT_FOUND);
        }
        Article article = convertArticleDTOToArticle(articleDTO, user);

        return ok(convertArticleToArticleDTO(articleService.addArticle(article, user)));
    }

    @PostMapping("/update")
    public ResponseEntity<ArticleDTO> updateArticle(@RequestBody ArticleDTO articleDTO) {

        User user = userService.getUser(articleDTO.getUserId());
        if (user == null) {
            return new ResponseEntity<>(articleDTO, HttpStatus.NOT_FOUND);
        }

        Article updatedArticle = convertArticleDTOToArticle(articleDTO, user);
        articleService.updateArticle(updatedArticle);

        return new ResponseEntity<>(articleDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getPageOfArticles(@RequestParam(name = "page", defaultValue = "0") int page,
                                                              @RequestParam(name = "limit", defaultValue = "50") int limit) {

        return ok(articleService.findArticlesOfPage(page, limit));

    }

    @GetMapping("/delete/{id}")
    public void deleteArticle(@PathVariable UUID id) {

        //TODO: check if this article is referenced elsewhere, or create a property on the article which is deleted (ZonedDateTime)
        //this way you dont delete the data from the db but check on the findAll and fndById whereDeletedIsNull

        articleService.deleteById(id);

    }

}
