package com.lendandborrow.service;

import com.lendandborrow.ExcepetionHandling.exceptions.ArticleServiceException;
import com.lendandborrow.model.Article;
import com.lendandborrow.model.User;
import com.lendandborrow.model.dto.ArticleDTO;
import com.lendandborrow.repositories.ArticleRepository;
import com.lendandborrow.utils.converters.ArticleConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.lendandborrow.utils.converters.ArticleConverter.convertArticleToArticleDTO;
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


    public ArticleDTO findById(UUID id) {
        return convertArticleToArticleDTO(articleRepository.findById(id).orElseThrow(() -> new ArticleServiceException("article id " + id.toString() + "not found")));
    }

    public Article addArticle(Article article, User user) {
        article.setOwner(user);
        articleRepository.save(article);
        return article;
    }

    public Article getArticle(UUID articleID) throws RuntimeException {
        return articleRepository.findById(articleID).orElseThrow(() -> new ArticleServiceException("article with id " + articleID + "not found"));
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

    //TODO : implement method
    public void deleteById(UUID articleId) {
    }


    public List<ArticleDTO> findArticlesOfPage(int page, int limit) {

        if (page < 0 | limit < 1) {
            throw new ArticleServiceException("Page and Limit must be > 0");
        }
        Pageable desiredPage = PageRequest.of(page, limit);
        return articleRepository.findAll(desiredPage)
                .getContent().stream().map(ArticleConverter::convertArticleToArticleDTO)
                .collect(Collectors.toList());


    }
}
