package com.bazydanychtest.user.tables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentService extends JpaRepository<Comment, Integer> {
/*    @Query("FROM comments WHERE articleid = 1")
    List<Comment> findByArticleID(Integer id);

    @Query("SELECT a FROM comments a WHERE a.articleid = ?1")*/
    List<Comment> findByArticleID(Integer articleID);


}
