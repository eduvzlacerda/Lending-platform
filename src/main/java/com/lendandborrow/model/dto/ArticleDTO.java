package com.lendandborrow.model.dto;

import com.lendandborrow.model.enums.EnumArticleStatus;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@AllArgsConstructor
@Getter
public class ArticleDTO {
    @NotNull
    private long id;
    @NotNull
    private String title;
    private String description;
    @NotNull
    private EnumArticleStatus articleStatus;
    @NotNull
    private final long userId;
}
