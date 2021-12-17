package com.lendandborrow.model.dto;

import com.lendandborrow.model.enums.EnumArticleStatus;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ArticleDTO {

    @NotNull
    private UUID userId;

    @NotNull
    private String title;

    private String description;

    private EnumArticleStatus articleStatus;

}
