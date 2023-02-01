package com.bazydanychtest.user.tables;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Integer> {

    List<Likes> findByUsername(String name);

    List findByArticleIDAndUsername(int id, String name);

    boolean existsByArticleIDAndUsername(int id, String name);

    //boolean findByArticleIDAndUsername(int id, String name);
}
