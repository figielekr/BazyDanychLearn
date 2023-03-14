package com.bazydanychtest.user.tables;

import jakarta.persistence.Column;

public class CommentExtra {
    private Integer id;
    private Integer articleID;
    private String comment;
    private String author;
    private Integer likes;
    private Integer disLikes;
    private String createDate;
    private String createTime;
    private Boolean isLiked;

    public CommentExtra() {
    }

    public CommentExtra(Integer id, Integer articleID, String comment, String author, Integer likes, String createDate, String createTime, Boolean isLiked) {
        this.id = id;
        this.articleID = articleID;
        this.comment = comment;
        this.author = author;
        this.likes = likes;
        this.createDate = createDate;
        this.createTime = createTime;
        this.isLiked = isLiked;
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

    public Boolean getLiked() {
        return isLiked;
    }

    public void setLiked(Boolean liked) {
        isLiked = liked;
    }

    @Override
    public String toString() {
        return "CommentExtra{" +
                "id=" + id +
                ", articleID=" + articleID +
                ", comment='" + comment + '\'' +
                ", author='" + author + '\'' +
                ", likes=" + likes +
                ", disLikes=" + disLikes +
                ", createDate='" + createDate + '\'' +
                ", createTime='" + createTime + '\'' +
                ", isLiked=" + isLiked +
                '}';
    }
}

