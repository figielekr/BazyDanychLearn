package com.bazydanychtest.user.tables;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public interface CommentService extends JpaRepository<Comment, Integer> {
/*    @Query("FROM comments WHERE articleid = 1")
    List<Comment> findByArticleID(Integer id);

    @Query("SELECT a FROM comments a WHERE a.articleid = ?1")*/
    List<Comment> findByArticleID(Integer articleID);

    default String timeCurrent(){
        Date date = new Date();
        String strDateFormat = "dd-MM-yyyy HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        return dateFormat.format(date);
    }



}
