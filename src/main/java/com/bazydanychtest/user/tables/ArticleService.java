package com.bazydanychtest.user.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired ArticleRepository repoArticle;
    @Autowired CommentRepository repoComment;

    public List<Article> findArticleWithSorting(String field){
        return repoArticle.findAll(Sort.by(Sort.Direction.DESC, field));
    }

    public Page<Article> findArticlesWithPagination(int offset, int pageSize){
        Page<Article> articles = repoArticle.findAll(PageRequest.of(offset, pageSize)
                .withSort(Sort.by(Sort.Direction.DESC, "id")));
        return articles;
    }
}
