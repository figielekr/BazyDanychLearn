package com.bazydanychtest;

import com.bazydanychtest.user.tables.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@EnableMethodSecurity
@EnableWebSecurity
@Controller
class ArticlesController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired private UserRepository repo;
    @Autowired private ArticleRepository repoArticle;
    @Autowired private CommentRepository repoComment;
    @Autowired private CommentService commentService;
    @Autowired private ArticleLikesRepository repoArticleLikes;
    @Autowired private FileUploadService fileUploadService;
    @Autowired private ArticleService articleService;
    @Autowired private CommentLikesRepository commentLikesRepository;
    @Autowired HttpServletRequest request;
    @GetMapping("display_articles")
    public String displayArticlesFirstPage(Model model){
//        final String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
//        model.addAttribute("currentUser", currentName);
//        model.addAttribute("comments", commentService.findAll());
//        model.addAttribute("likes", repoArticleLikes.findByUsername(currentName));
//        model.addAttribute("articleSize", repoArticle.count());
//        List <Integer> ids = articleService.getArticleIds(0, 5);
//        List<ArticleExtra> articles = new ArrayList<>();
//        int size = Math.toIntExact(repoArticle.count()); //zamiana long na int
//        System.out.println(size);
//        for (int i=0; i<ids.size(); i++){
//            if(repoArticle.existsById(ids.get(i))){
//                articles.add(articleService.getArticles(ids.get(i), currentName));
//                System.out.println(ids.get(i) + " " +i);
//            } else {
//                System.out.println("blad przy id artykulu: Display_articles");
//            }
//        }
//        System.out.println(articleService.getArticleIds(0, 10));
//        model.addAttribute("articles", articles);
        common("display_articles", model, 0);
        return "display_articles";
    }

    @GetMapping("display_articles/{page}")
    public String displayArticles(Model model, @PathVariable Integer page){
        page -= 1;
        common("display_articles", model, page);
        return "display_articles";
    }
    @GetMapping("display_articles/")
    public String displayArticlesWithoutInt(Model model){
        common("display_articles", model, 0);
        return "display_articles";
    }
    private String common(String path, Model model, int page){


        final String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentUser", currentName);
        model.addAttribute("comments", commentService.findAll());
        model.addAttribute("likes", repoArticleLikes.findByUsername(currentName));
        model.addAttribute("articleSize", repoArticle.count());
        List <Integer> ids = articleService.getArticleIds(page, 5);
        List<ArticleExtra> articles = new ArrayList<>();
        int size = Math.toIntExact(repoArticle.count()); //zamiana long na int
        System.out.println(size);
        for (int i=0; i<ids.size(); i++){
            if(repoArticle.existsById(ids.get(i))){
                articles.add(articleService.getArticles(ids.get(i), currentName));
                System.out.println(ids.get(i) + " " +i);
            } else {
                System.out.println("blad przy id artykulu: Display_articles");
            }
        }
        model.addAttribute("articles", articles);
        return path;
    }
}