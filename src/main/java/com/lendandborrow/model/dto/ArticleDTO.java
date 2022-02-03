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

    private UUID id;

    @NotNull
    private UUID userId;

    private String ownerName;

    @NotNull
    private String title;

    private String description;

    private EnumArticleStatus articleStatus;

}
