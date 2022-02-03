package com.lendandborrow.utils.converters;

import com.lendandborrow.model.Article;
import com.lendandborrow.model.User;
import com.lendandborrow.model.dto.ArticleDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ArticleConverter {

    public static ArticleDTO convertArticleToArticleDTO(Article article) {
        return ArticleDTO.builder()
                .id(article.getId())
                .articleStatus(article.getArticleStatus())
                .description(article.getDescription())
                .userId(article.getOwner().getId())
                .ownerName(article.getOwner().getName())
                .title(article.getTitle())
                .build();
    }
    public static Article convertArticleDTOToArticle(ArticleDTO articleDTO, User owner) {
        return Article.builder()
                .id(articleDTO.getId())
                .articleStatus(articleDTO.getArticleStatus())
                .description(articleDTO.getDescription())
                .owner(owner)
                .title(articleDTO.getTitle())
                .build();
    }
}
