package com.bazydanychtest;

import com.bazydanychtest.security.UserInfoUserDetails;
import com.bazydanychtest.user.tables.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
    @Autowired private LikesRepository repoLikes;
    @Autowired private FileUploadService fileUploadService;
    @Autowired private ArticleService articleService;

    @GetMapping("")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("add_user")
    public String addUser(Model model){
        String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentUser", currentName);
        System.out.println(currentName);
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
        System.out.println(user);
        repo.save(user);
        redirectAttributes.addFlashAttribute("user", "saffsfdsfdsfdsfdsfds");
        return "redirect:/index";
    }

    @GetMapping("display_profile")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'user', 'admin')")
    public String displayPorifle(Model model){
        String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentName", currentName);
        model.addAttribute("user", repo.findByUserName(currentName));
        model.addAttribute("comments", repoComment.findByAuthor(currentName));
        model.addAttribute("articles", repoArticle.findByAuthor(currentName));

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
        String path = fileUploadService.uploadImage(multipartFile);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Article article = new Article(userName, title, 0, 0, path,repo.findByUserName(userName).get().getPath() ,commentService.dateCurrent(), commentService.timeCurrent());
        System.out.println(article);
        repoArticle.save(article);
        return "redirect:/display_articles";
    }
    @GetMapping("add_comment")
    public String addComment(){
        return "add_comment";
    }
    @RequestMapping(value = "add_comment_from_article/{id}", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'user', 'admin')")
    public Comment  addCommentFromArticle(
            @PathVariable int id,
            @RequestBody ObjectNode commentContent
    ){
        String content = commentContent.get("comment").asText();
        System.out.println(content);
        String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
        Comment comment = new Comment(id, content, currentName, 0, 0,  commentService.dateCurrent(), commentService.timeCurrent());
        System.out.println(comment);
        repoComment.save(comment);
        //return "redirect:/display_articles";
        return comment;
    }
    @RequestMapping(value = "ajaxTestRequest/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Comment test(@RequestBody ObjectNode content,
                        @PathVariable int id){
        String commentContent = content.get("comment").asText();
        Comment comment = new Comment(id, commentContent, "gal anonim",0, 0, commentService.dateCurrent(), commentService.timeCurrent());
        repoComment.save(comment);
        System.out.println(comment);
        return comment;

    }
    @GetMapping("display_articles")
    public String displayArticles(Model model){
        final String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(currentName);
        model.addAttribute("currentUser", currentName);
        model.addAttribute("articles", repoArticle.findAll());
        model.addAttribute("comments", commentService.findAll());
        model.addAttribute("likes", repoLikes.findByUsername(currentName));
        //model.addAttribute("articles", articleService.findArticleWithSorting("id"));
        //model.addAttribute("articles", articleService.findArticlesWithPagination(id, 5));
        //model.addAttribute("articles", repoArticle.findAll(Sort.by(Sort.Direction.ASC, )));
        //System.out.println(model);
        return "display_articles";
    }
    @PostMapping("afterLogin")
    public String afterLogin(){
        return "redirect:/display_articles";
    }

    @GetMapping("show_users")
    public String showUsers(Model model){
        model.addAttribute("users", repo.findAll());
        System.out.println(model);
        return "users";
    }
    @GetMapping("/adduser/{cos}")
    public String test(@PathVariable String cos, Model model){
        final String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentUser", currentName);
        System.out.println(currentName);
        String link = "redirect:/" + cos;
        return cos;
    }
    @GetMapping("/{id}")
    public String displayArticleById(@PathVariable int id, Model model){
        //repoArticle.findById(id).ifPresent(cos -> model.addAttribute("article", repoArticle.findById(id)));
        model.addAttribute("article", repoArticle.findById(id));
        List<Comment> comment = commentService.findByArticleID(id);
        model.addAttribute("comments", comment);
        String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentUser", currentName);
        return "article";

    }

    @PostMapping("/addNewArticleComment")
    public String addNewArticleComment(){
        int id = 2;
        //return "article";
        return "index";
    }
    @GetMapping("/likes/{id}")
    public String addLike (@PathVariable int id, Model model){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (repoLikes.existsByArticleIDAndUsername(id, name)){
            repoLikes.deleteById(id);

        } else {
            Likes like = new Likes(name, id, commentService.dateCurrent() + " "+ commentService.timeCurrent());
            repoLikes.save(like);
        }

        return "redirect:/display_articles";

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
    public @ResponseBody List<Comment> getComments(@PathVariable int id){ //retrieve comments
        return repoComment.findByArticleID(id);
    }
    @GetMapping("ajaxtest")
    public String lol (){
        return "ajaxtest";
    }

}
