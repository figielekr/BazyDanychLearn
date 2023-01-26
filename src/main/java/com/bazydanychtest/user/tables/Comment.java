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
    private String createDate;
    @Column(nullable = false, length = 40)
    private Integer likes;
    @Column(nullable = false, length = 40)
    private Integer dislikes;

    public Comment() {
    }

    public Comment(Integer articleID, String comment, String author, String createDate, Integer likes, Integer dislikes) {
        this.id = id;
        this.articleID = articleID;
        this.comment = comment;
        this.author = author;
        this.createDate = createDate;
        this.likes = likes;
        this.dislikes = dislikes;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", articleID=" + articleID +
                ", comment='" + comment + '\'' +
                ", author='" + author + '\'' +
                ", createDate='" + createDate + '\'' +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                '}';
    }
}
