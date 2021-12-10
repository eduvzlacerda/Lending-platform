package com.lendandborrow.model;

import com.lendandborrow.model.enums.EnumArticleStatus;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

import static com.lendandborrow.model.enums.EnumArticleStatus.HIDDEN;

@Entity
@Table(name="articles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Article {
//TODO change id to UUID
//TODO change reference to user to a single id and not a user reference ?

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

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
