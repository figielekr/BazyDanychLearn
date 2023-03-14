package com.bazydanychtest.user.tables;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByAuthor(String userName);
    List<Comment> findByArticleID(int id);


    Integer countByAuthor(String currentName);

    Integer countByArticleID(Integer id);
}
