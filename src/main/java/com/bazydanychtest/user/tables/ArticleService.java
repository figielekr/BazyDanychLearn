package com.bazydanychtest.user.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired private ArticleRepository repoArticle;
    @Autowired private CommentRepository repoComment;
    @Autowired private ArticleLikesRepository articleLikesRepository;
    @Autowired private CommentLikesRepository commentLikesRepository;

    public List<Article> findArticleWithSorting(String field){
        return repoArticle.findAll(Sort.by(Sort.Direction.DESC, field));
    }

    public Page<Article> findArticlesWithPagination(int offset, int pageSize){
        Page<Article> articles = repoArticle.findAll(PageRequest.of(offset, pageSize)
                .withSort(Sort.by(Sort.Direction.DESC, "id")));
        return articles;
    }

//    public List<Integer> getArticleIds(int pageNumber, int pageSize){
//        Page <Article> articles = repoArticle.findAll(PageRequest.of(pageNumber, pageSize));
//
//        for(int i=0; i<articles.getSize(); i++){
//            System.out.println(articles.get());
//        }
//
//        return null;
//    }
        public List<Integer> getArticleIds(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.DESC, "id"));
        List<Integer> articleIds = new ArrayList<>();
        Slice<Article> articleSlice = repoArticle.findAll(pageable);
        //while (articleSlice.hasContent()){
            articleSlice.forEach(article -> articleIds.add(article.getId()));
            pageable = pageable.next();
            //articleSlice = repoArticle.findAll(pageable);
        //}
        System.out.println(articleIds);
        //System.out.println(articleIds.getClass());
        return articleIds;
    }



    public ArticleExtra getArticles(Integer id, String user){
        Optional<Article> article = repoArticle.findById(id);
        Boolean isLiked = articleLikesRepository.existsByArticleIDAndUsername(id, user);
        //if(articleLikes.existsByArticleIDAndUsername())
        ArticleExtra articleNew = new ArticleExtra(article.get().getId(), article.get().getAuthor(), article.get().getTitle(),article.get().getLikes(),
                article.get().getPath(), article.get().getAuthorPath(), article.get().getCreateDate(), article.get().getCreateTime(), isLiked);
        return articleNew;
    }
    public List<CommentExtra> getComments(Integer id, String user){
        List<Comment> comment = repoComment.findByArticleID(id);
        List<CommentExtra> commentExtraList = new ArrayList<>();
        for (int i=0 ; i<comment.size(); i++) {
            //System.out.println(comment.get(i));
            Boolean isLiked = commentLikesRepository.existsByCommentIDAndUsername(comment.get(i).getId(), user);
            //System.out.println(id);
            //System.out.println(user);
            CommentExtra commentExtra = new CommentExtra(comment.get(i).getId(), comment.get(i).getArticleID(),comment.get(i).getComment(), comment.get(i).getAuthor(), comment.get(i).getLikes(), comment.get(i).getCreateDate(), comment.get(i).getCreateTime(), isLiked);
            commentExtraList.add(commentExtra);
        }
        //System.out.println(commentExtraList);
        return commentExtraList;
    }

    public List<Article> getProfileArticlesInfo(String currentName) {
        List<Article> articles = repoArticle.findByAuthor(currentName);
        List<Article> articlesNew = new ArrayList<>();
        for (int i=0 ; i<articles.size(); i++) {
            Article article = new Article(articles.get(i).getId(), articles.get(i).getTitle(), articles.get(i).getLikes(), articles.get(i).getCreateDate(), articles.get(i).getCreateTime(), repoComment.countByArticleID(articles.get(i).getId()));
//            System.out.println(repoComment.countByAuthor(currentName));
//            System.out.println(article);
            articlesNew.add(article);
        }
        //System.out.println(articlesNew);
        return articlesNew;
    }

    public List<Comment> getProfileComments(String currentName) {
        List<Comment> comments = repoComment.findByAuthor(currentName);
        List<Comment> commentsNew = new ArrayList<>();
        for (int i=0; i<comments.size(); i++){
            String title = repoArticle.findById(comments.get(i).getArticleID()).get().getTitle();
            Comment comment = new Comment(comments.get(i).getArticleID(), comments.get(i).getComment(), comments.get(i).getLikes(), comments.get(i).getCreateDate(), comments.get(i).getCreateTime(), title);
            //System.out.println(comment);
            commentsNew.add(comment);
        }
        return commentsNew;
    }
}



