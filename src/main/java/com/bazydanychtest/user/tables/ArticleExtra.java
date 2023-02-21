package com.bazydanychtest.user.tables;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ArticleExtra {
    private Integer id;
    private String author;
    private String title;
    private Integer likes;
    private String path;
    private String authorPath;
    private String createDate;
    private String createTime;
    private Boolean isLiked;

    public ArticleExtra(Integer id, String author, String title, Integer likes, String path, String authorPath, String createDate, String createTime, Boolean isLiked) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.likes = likes;
        this.path = path;
        this.authorPath = authorPath;
        this.createDate = createDate;
        this.createTime = createTime;
        this.isLiked = isLiked;
    }

    public ArticleExtra() {
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

    public Boolean getLiked() {
        return isLiked;
    }

    public void setLiked(Boolean liked) {
        isLiked = liked;
    }

    @Override
    public String toString() {
        return "ArticleExtra{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", likes=" + likes +
                ", path='" + path + '\'' +
                ", authorPath='" + authorPath + '\'' +
                ", createDate='" + createDate + '\'' +
                ", createTime='" + createTime + '\'' +
                ", isLiked=" + isLiked +
                '}';
    }
}
