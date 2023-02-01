package com.bazydanychtest.user.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "likes")
public class Likes {

    @Column (nullable = false)
    String username;
    @Id
    @Column (nullable = false)
    Integer articleID;
    @Column (nullable = false)
    String likeTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getArticleID() {
        return articleID;
    }

    public void setArticleID(Integer articleID) {
        this.articleID = articleID;
    }

    public String getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(String likeTime) {
        this.likeTime = likeTime;
    }

    public Likes(String username, Integer articleID, String likeTime) {
        this.username = username;
        this.articleID = articleID;
        this.likeTime = likeTime;
    }

    public Likes() {
    }
}
