package com.bazydanychtest.user.tables;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleLikesRepository extends JpaRepository<ArticleLikes, Integer> {

    List<ArticleLikes> findByUsername(String name);

    List findByArticleIDAndUsername(int id, String name);

    boolean existsByArticleIDAndUsername(int id, String name);
    @Transactional
    void deleteByArticleIDAndUsername(int id, String name);

    //boolean findByArticleIDAndUsername(int id, String name);
}
