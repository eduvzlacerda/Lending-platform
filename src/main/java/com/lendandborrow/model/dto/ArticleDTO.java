package com.lendandborrow.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lendandborrow.model.enums.EnumArticleStatus;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder(toBuilder = true)
@AllArgsConstructor
@Getter
public class ArticleDTO {

    private long id;

    @NotNull
    private String title;

    private String description;

    @NotNull
    @JsonIgnore
    private EnumArticleStatus articleStatus;

    @NotNull
    private final long userId;
}
