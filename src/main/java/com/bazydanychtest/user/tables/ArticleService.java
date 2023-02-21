package com.bazydanychtest.user.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired ArticleRepository repoArticle;
    @Autowired CommentRepository repoComment;
    @Autowired
    private ArticleLikesRepository articleLikesRepository;

    public List<Article> findArticleWithSorting(String field){
        return repoArticle.findAll(Sort.by(Sort.Direction.DESC, field));
    }

    public Page<Article> findArticlesWithPagination(int offset, int pageSize){
        Page<Article> articles = repoArticle.findAll(PageRequest.of(offset, pageSize)
                .withSort(Sort.by(Sort.Direction.DESC, "id")));
        return articles;
    }

    public ArticleExtra getArticles(Integer id, String user){
        Optional<Article> article = repoArticle.findById(id);

        Boolean isLikes = articleLikesRepository.existsByArticleIDAndUsername(id, user);
        //if(articleLikes.existsByArticleIDAndUsername())
        ArticleExtra articleNew = new ArticleExtra(article.get().getId(), article.get().getAuthor(), article.get().getTitle(),article.get().getLikes(), article.get().getPath(), article.get().getAuthorPath(), article.get().getCreateDate(), article.get().getCreateTime(), isLikes);
        return articleNew;
    }
}
