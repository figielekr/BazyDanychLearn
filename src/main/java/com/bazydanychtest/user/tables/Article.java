package com.bazydanychtest.user.tables;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
/*
import org.hibernate.annotations.CreationTimestamp;
*/

import java.sql.Timestamp;

@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String author;
    @Column
    private String title;
    @Column
    private Integer likes;
    @Column
    private Integer dislikes;
    @Column
    private String path;
    @Column
    private String authorPath;

    @Column
    private String createDate;
    @Column
    private String createTime;

    public Article() {
    }

    public Article(String author, String title, Integer likes, Integer dislikes, String path, String authorPath, String createDate, String createTime) {
        this.author = author;
        this.title = title;
        this.likes = likes;
        this.dislikes = dislikes;
        this.path = path;
        this.authorPath = authorPath;
        this.createDate = createDate;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAuthorPath() {
        return authorPath;
    }

    public void setAuthorPath(String authorPath) {
        this.authorPath = authorPath;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", path='" + path + '\'' +
                ", authorPath='" + authorPath + '\'' +
                ", createDate='" + createDate + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
