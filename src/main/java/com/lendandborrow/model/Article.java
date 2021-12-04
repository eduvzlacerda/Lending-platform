package com.lendandborrow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lendandborrow.model.enums.EnumArticleStatus;

import javax.persistence.*;

@Entity
@Table(name="articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;

    @Column(name = "title")
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private EnumArticleStatus articleStatus;

    @ManyToOne
    @JsonIgnore
    private User owner;

    public Article() {
        this.title = "";
        this.description = "";
        this.articleStatus = EnumArticleStatus.AVAILABLE;
    }

    public Article(String title, String description, EnumArticleStatus articleStatus) {
        this.title = title;
        this.description = description;
        this.articleStatus = articleStatus;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public EnumArticleStatus getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(EnumArticleStatus articleStatus) {
        this.articleStatus = articleStatus;
    }
}
