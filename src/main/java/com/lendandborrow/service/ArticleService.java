package com.lendandborrow.service;

import com.lendandborrow.ExcepetionHandling.exceptions.ArticleServiceException;
import com.lendandborrow.model.Article;
import com.lendandborrow.model.User;
import com.lendandborrow.model.dto.ArticleDTO;
import com.lendandborrow.repositories.ArticleRepository;
import com.lendandborrow.utils.converters.ArticleConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.lendandborrow.utils.converters.ArticleConverter.convertArticleToArticleDTO;


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
        return convertArticleToArticleDTO(articleRepository.findById(id).get());
    }

    public Article addArticle(Article article, User user) {
        article.setOwner(user);
        articleRepository.save(article);
        return article;
    }

    public Article getArticle(UUID articleID) throws RuntimeException {
        return articleRepository.findById(articleID).orElseThrow(() -> new ArticleServiceException("article with id '" + articleID + "' not found"));
    }

    //TODO : implement method
    public void deleteById(UUID articleId) {
    }
}
