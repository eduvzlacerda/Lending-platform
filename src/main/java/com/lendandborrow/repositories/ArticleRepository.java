package com.lendandborrow.repositories;

import com.lendandborrow.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query(
            value = "SELECT a FROM Article a WHERE (lower(a.title) LIKE %:searchString%) AND (a.articleStatus = 'AVAILABLE')"
    )
        // No specified Query needed because Spring JPA supports generating queries using the method name
    List<Article> findBySearch(@Param("searchString") String searchString);
}