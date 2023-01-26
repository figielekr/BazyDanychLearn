package com.bazydanychtest;

import com.bazydanychtest.user.tables.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired private UserRepository repo;
    @Autowired private ArticleRepository repoArticle;
    @Autowired private CommentRepository repoComment;
    @Autowired private CommentService commentService;
    @GetMapping("")
    public String showHomePage() {
        return "index";
    }
    @GetMapping("add_user")
    public String addUser(){
        return "add_user";
    }
    @RequestMapping("add_user_create")
        public String createNewUser(
                @RequestParam(name = "firstname", required = false) String firstname,
                @RequestParam(name = "lastname", required = false) String lastname,
                @RequestParam(name = "password", required = false) String password,
                @RequestParam(name = "email", required = false) String email
    ){
        User user = new User(firstname, lastname, email, password);
        System.out.println(user);
        repo.save(user);
        return "index";
    }
    @GetMapping("add_article")
    public String addArticle(){
        return "add_article";
    }
    @RequestMapping("add_article_create")
    public String addArticleCreate(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "author", required = false) String author
    ){
        Article article = new Article(author, title, 0, 0);
        System.out.println(article);
        repoArticle.save(article);
        return "index";
    }
    @GetMapping("add_comment")
    public String addComment(){
        return "add_comment";
    }
    @RequestMapping("add_comment_create")
    public String addCommentCreate(
            @RequestParam(name = "articleid", required = false) Integer articleid,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "comment", required = false) String commentcontent
    ){
        Comment comment = new Comment(articleid,  commentcontent, author, "d", 0, 0);
        System.out.println(comment);
        repoComment.save(comment);
        return "index";
    }
    @RequestMapping("add_comment_from_article")
    public String addCommentFromArticle(
            /*@ModelAttribute ("comment") Comment comment,*/
            @RequestParam(name = "ArticleID", required = false) Integer articleID,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "content", required = false) String content

    ){
        Comment comment = new Comment(articleID, content, author, "n", 0, 0);
        repoComment.save(comment);
        System.out.println(comment);
        return "index";
    }
    @GetMapping("display_articles")
    public String displayArticles(Model model){
        model.addAttribute("articles", repoArticle.findAll());
        model.addAttribute("comments", commentService.findAll());
        System.out.println(model);
        return "display_articles";
    }

    @GetMapping("show_users")
    public String showUsers(Model model){
        model.addAttribute("users", repo.findAll());
        System.out.println(model);
        return "users";
    }
    @GetMapping("/{id}")
    public String displayArticleById(@PathVariable int id, Model model){
        //repoArticle.findById(id).ifPresent(cos -> model.addAttribute("article", repoArticle.findById(id)));
        model.addAttribute("article", repoArticle.findById(id));
        List<Comment> comment = commentService.findByArticleID(id);
        model.addAttribute("comments", comment);

        return "article";

    }

    @PostMapping("/addNewArticleComment")
    public String addNewArticleComment(){
        int id = 2;
        //return "article";
        return "index";
    }

}
