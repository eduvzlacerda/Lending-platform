package com.lendandborrow.service;

import com.lendandborrow.model.Article;
import com.lendandborrow.model.User;
import com.lendandborrow.model.dto.ArticleDTO;
import com.lendandborrow.repositories.ArticleRepository;
import com.lendandborrow.utils.converters.ArticleConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;


@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<ArticleDTO> findAllArticles() {
        return articleRepository.findAll()
                .stream()
                .map(ArticleConverter::convertArticleToArticleDTO)
                .collect(Collectors.toList());
    }

    public Article addArticle(Article article, User user) {
        article.setOwner(user);
        articleRepository.save(article);
        return article;
    }

    public ResponseEntity<List<ArticleDTO>> searchArticlesbyString(String searchString) {
        if (searchString == null) {
            return ok(findAllArticles());
        }
        return ok(articleRepository.findBySearch(searchString)
                .stream()
                .map(ArticleConverter::convertArticleToArticleDTO)
                .collect(Collectors.toList()));
    }

    public void deleteById(Long articleId) {

        //TODO: return object?

        this.articleRepository.deleteById(articleId);

    }
}
