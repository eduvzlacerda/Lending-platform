package com.lendandborrow.service;

import com.lendandborrow.model.Article;
import com.lendandborrow.model.User;
import com.lendandborrow.model.dto.ArticleDTO;
import com.lendandborrow.model.dto.UserDTO;
import com.lendandborrow.repositories.ArticleRepository;
import com.lendandborrow.utils.converters.ArticleConverter;
import com.lendandborrow.utils.converters.UserConverter;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserService userService;

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

    public void deleteById(Long articleId) {
    }
}
