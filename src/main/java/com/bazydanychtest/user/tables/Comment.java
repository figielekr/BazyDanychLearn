package com.bazydanychtest.user.tables;

import jakarta.persistence.*;
@Entity
@Table(name = "comments")
public class Comment {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer articleID;

    @Column(nullable = false, length = 1000)
    private String comment;
    @Column(nullable = false, length = 40)
    private String author;
    @Column(nullable = false, length = 40)
    private Integer likes;
    @Column(nullable = false, length = 40)
    private Integer disLikes;
    @Column(nullable = false, length = 40)
    private String createDate;
    @Column(nullable = false, length = 40)
    private String createTime;

    public Comment() {
    }

    public Comment(Integer articleID, String comment, String author, Integer likes, Integer disLikes, String createDate, String createTime) {
        this.articleID = articleID;
        this.comment = comment;
        this.author = author;
        this.likes = likes;
        this.disLikes = disLikes;
        this.createDate = createDate;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleID() {
        return articleID;
    }

    public void setArticleID(Integer articleID) {
        this.articleID = articleID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDisLikes() {
        return disLikes;
    }

    public void setDisLikes(Integer disLikes) {
        this.disLikes = disLikes;
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
        return "Comment{" +
                "id=" + id +
                ", articleID=" + articleID +
                ", comment='" + comment + '\'' +
                ", author='" + author + '\'' +
                ", likes=" + likes +
                ", disLikes=" + disLikes +
                ", createDate='" + createDate + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
