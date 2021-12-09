package com.lendandborrow.utils.converters;

import com.lendandborrow.model.Article;
import com.lendandborrow.model.User;
import com.lendandborrow.model.dto.ArticleDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ArticleConverter {

    public static ArticleDTO convertArticleToArticleDTO(Article article) {
        return ArticleDTO.builder()
                .articleStatus(article.getArticleStatus())
                .id(article.getId())
                .description(article.getDescription())
                .userId(article.getOwner().getId())
                .title(article.getTitle())
                .build();
    }
    public static Article convertArticleDTOToArticle(ArticleDTO articleDTO, User owner) {
        return Article.builder()
                .articleStatus(articleDTO.getArticleStatus())
                .id(articleDTO.getId())
                .description(articleDTO.getDescription())
                .owner(owner)
                .title(articleDTO.getTitle())
                .build();
    }
}
