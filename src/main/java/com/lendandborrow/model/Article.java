package com.lendandborrow.model;

import com.lendandborrow.model.enums.EnumArticleStatus;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

import java.util.UUID;

import static com.lendandborrow.model.enums.EnumArticleStatus.HIDDEN;

@Entity
@Table(name="articles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Article {
//TODO Check why Notnoll constraint for status is not working

    @Id
    @NotNull
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @Column(name = "title")
    @NotNull
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @NotNull
    private EnumArticleStatus articleStatus = HIDDEN;

    @ManyToOne
    @NotNull
    private User owner;
}
