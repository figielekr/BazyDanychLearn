package com.bazydanychtest;

import com.bazydanychtest.user.tables.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@EnableMethodSecurity
@EnableWebSecurity
@Controller
public class MainController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired private UserRepository repo;
    @Autowired private ArticleRepository repoArticle;
    @Autowired private CommentRepository repoComment;
    @Autowired private CommentService commentService;
    @Autowired private ArticleLikesRepository repoArticleLikes;
    @Autowired private FileUploadService fileUploadService;
    @Autowired private ArticleService articleService;
    @Autowired private Environment environment;
    @Autowired private CommentLikesRepository commentLikesRepository;
    @Autowired private HttpServletRequest request;

//    @GetMapping("")
//    public String showHomePage() {
//        return "index";
//    }
//    @GetMapping("display_articles")
//    public String displayArticles(Model model){
//        final String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
//        System.out.println(currentName);
//        model.addAttribute("currentUser", currentName);
//        //model.addAttribute("articles", repoArticle.findAll());
//        model.addAttribute("comments", commentService.findAll());
//        model.addAttribute("likes", repoArticleLikes.findByUsername(currentName));
//        System.out.println(repoArticleLikes.findByUsername(currentName));
//        List<ArticleExtra> articles = new ArrayList<>();
//        int size = Math.toIntExact(repoArticle.count());
//        System.out.println(size);
//        for (int i=size; i>=1; i--){
//            articles.add(articleService.getArticles(i, currentName));
//        }
//        System.out.println(articles);
//        model.addAttribute("articles", articles);
//        //model.addAttribute("articles", articleService.findArticleWithSorting("id"));
//        //model.addAttribute("articles", articleService.findArticlesWithPagination(id, 5));
//        //model.addAttribute("articles", repoArticle.findAll(Sort.by(Sort.Direction.ASC, )));
//        //System.out.println(model);
//        return "display_articles";
//    }

    @GetMapping("add_user")
    public String addUser(Model model){
        String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentUser", currentName);
        return "add_user";
    }
    @RequestMapping("add_user_create")
        public String createNewUser(
                @RequestParam(name = "username", required = false) String username,
                @RequestParam(name = "password", required = false) String password,
                @RequestParam(name = "sex", required = false) String sex,
                @RequestParam(name = "email", required = false) String email,
                @RequestParam(name = "role", required = false) String role,
                RedirectAttributes redirectAttributes
    ){
    //public User(String userName, String firstName, String lastName, String email, String sex, String password, String role, String path, String createDate, String lastVisit) {

        User user = new User(username, email, sex, password, "USER", commentService.dateCurrent()+" "+commentService.timeCurrent(), sex + ".jpg");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
        redirectAttributes.addFlashAttribute("user", "saffsfdsfdsfdsfdsfds");
        return "redirect:/index";
    }

    @GetMapping("display_profile")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'user', 'admin')")
    public String displayPorifle(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            HttpSession session = request.getSession();
            long sessionTimeout = session.getMaxInactiveInterval();
            long sessionTimeLeft = (sessionTimeout * 1000 - (System.currentTimeMillis() - session.getLastAccessedTime()))/*/1000/60*/;
            model.addAttribute("sessionTimeLeft", sessionTimeLeft);

            //System.out.println(sessionTimeLeft);
        }
        String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentName", currentName);
        model.addAttribute("user", repo.findByUserName(currentName));
        model.addAttribute("comments", articleService.getProfileComments(currentName));
        //model.addAttribute("articles", repoArticle.findByAuthor(currentName));
        model.addAttribute("articles", articleService.getProfileArticlesInfo(currentName));
        final String lol = environment.getProperty("filesUpload");
        return "profile";
    }
    @GetMapping("index")
    public String justIndex(){
        return "index";
    }
    @GetMapping("add_article")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'user', 'admin')")
    public String addArticle(){
        return "add_article";
    }
    @RequestMapping("add_article_create")
    public String addArticleCreate(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(value = "file") MultipartFile multipartFile
    ) throws IOException {
        System.out.println(multipartFile.getContentType().equals("image/jpeg"));
        System.out.println(multipartFile.getContentType().equals("image/png"));
        System.out.println(multipartFile.getContentType().equals("image/gif"));
        System.out.println(multipartFile.getSize());
        if(!multipartFile.getContentType().equals("image/gif") && !multipartFile.getContentType().equals("image/jpeg") && !multipartFile.getContentType().equals("image/png") || multipartFile.getSize() > 1024*1024){
            return "redirect:/add_article";
        }
        String path = fileUploadService.uploadImage(multipartFile);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Article article = new Article(userName, title, 0, 0, path,repo.findByUserName(userName).get().getPath() ,commentService.dateCurrent(), commentService.timeCurrent());
        //System.out.println(article);
        repoArticle.save(article);
        return "redirect:/display_articles";
    }
    @GetMapping("add_comment")
    public String addComment(){
        return "add_comment";
    }
    @RequestMapping(value = "add_comment_from_article/{id}", method = RequestMethod.POST)
    @ResponseBody
    //@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'user', 'admin')")
    public Comment  addCommentFromArticle(
            @PathVariable int id,
            @RequestBody ObjectNode commentContent
    ){
        String content = commentContent.get("comment").asText();
        //System.out.println(content);
        String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
        Comment comment = new Comment(id, content, currentName, 0, 0,  commentService.dateCurrent(), commentService.timeCurrent());
        //System.out.println(comment);
        repoComment.save(comment);
        //return "redirect:/display_articles";
        return comment;
    }

    @PostMapping("afterLogin")
    public String afterLogin(){
        return "redirect:/display_articles";
    }

    @GetMapping("show_users")
    public String showUsers(Model model){
        model.addAttribute("users", repo.findAll());
        //System.out.println(model);
        return "users";
    }
    @GetMapping("/adduser/{cos}")
    public String test(@PathVariable String cos, Model model){
        final String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentUser", currentName);
        //System.out.println(currentName);
        String link = "redirect:/" + cos;
        return cos;
    }
    @GetMapping("/{id}")
    public String displayArticleById(@PathVariable int id, Model model){
        final String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
        //repoArticle.findById(id).ifPresent(cos -> model.addAttribute("article", repoArticle.findById(id)));
        //model.addAttribute("article", repoArticle.findById(id));
        model.addAttribute("article", articleService.getArticles(id, currentName));
        List<Comment> comment = commentService.findByArticleID(id);
        model.addAttribute("comments", comment);
        model.addAttribute("currentUser", currentName);
        return "article";

    }

    @PostMapping("/addNewArticleComment")
    public String addNewArticleComment(){
        int id = 2;
        //return "article";
        return "index";
    }

    @GetMapping("/article_likes/{id}")
    public @ResponseBody Integer addArticleLike (@PathVariable int id){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Article> article = repoArticle.findById(id);
        int likes = article.get().getLikes();
        if (repoArticleLikes.existsByArticleIDAndUsername(id, name)){
            //repoArticleLikes.findByArticleIDAndUsername(id, name);
            //repoArticleLikes.deleteById(id);
            repoArticleLikes.deleteByArticleIDAndUsername(id, name);
            likes -=1;
            article.get().setLikes(likes);
            repoArticle.save(article.get());
            return likes;
        } else {
            ArticleLikes like = new ArticleLikes(name, id, commentService.dateCurrent() + " "+ commentService.timeCurrent());
            repoArticleLikes.save(like);
            System.out.println(like);
            likes +=1;
            article.get().setLikes(likes);
            repoArticle.save(article.get());
            return likes;
        }
        //return null;

    }
    @GetMapping("/comment_likes/{id}")
    public @ResponseBody Integer addCommentLike (@PathVariable int id){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Comment> comment = repoComment.findById(id);
        if (commentLikesRepository.existsByCommentIDAndUsername(id, name)){
            //commentLikesRepository.deleteById(id);
            commentLikesRepository.deleteByCommentIDAndUsername(id, name);
            int amountLikes = comment.get().getLikes()-1;
            comment.get().setLikes(amountLikes);
            repoComment.save(comment.get());
            return amountLikes;
        } else {
            CommentLikes commentLikes = new CommentLikes(id, name, commentService.dateCurrent()+" "+commentService.timeCurrent());
            commentLikesRepository.save(commentLikes);
            int amountLikes = comment.get().getLikes()+1;
            comment.get().setLikes(amountLikes);
            repoComment.save(comment.get());
            return amountLikes;
        }
    }
    @PostMapping("/image/upload")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'user', 'admin')")
    public String uploadImage(@RequestParam(value = "file") MultipartFile multipartFile) throws IOException {
        //fileUploadService.uploadImage(multipartFile);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = repo.findByUserName(userName);
        user.get().setPath(fileUploadService.uploadImage(multipartFile));
        repo.save(user.get());
        return "redirect:/display_profile";
    }
    @GetMapping("/comment/{id}")
    public @ResponseBody List<CommentExtra> getComments(@PathVariable int id){ //retrieve comments
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        //articleService.getComments(id, userName);
        //return repoComment.findByArticleID(id);
        return articleService.getComments(id, userName);
    }
    @GetMapping("ajaxtest")
    public String lol (){
        return "ajaxtest";
    }
    //@GetMapping("hasVoted")
    //public boolean


}
