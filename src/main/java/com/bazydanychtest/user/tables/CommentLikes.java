package com.bazydanychtest.user.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "comment_likes")
public class CommentLikes {
    @Id
    @Column(nullable = false)
    int CommentID;
    @Column(nullable = false)
    String username;
    @Column(nullable = false)
    String LikeDate;

    public CommentLikes() {
    }

    public CommentLikes(int commentID, String username, String likeDate) {
        CommentID = commentID;
        this.username = username;
        LikeDate = likeDate;
    }

    public int getCommentID() {
        return CommentID;
    }

    public void setCommentID(int commentID) {
        CommentID = commentID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLikeDate() {
        return LikeDate;
    }

    public void setLikeDate(String likeDate) {
        LikeDate = likeDate;
    }

    @Override
    public String toString() {
        return "CommentLikes{" +
                "CommentID=" + CommentID +
                ", username='" + username + '\'' +
                ", LikeDate='" + LikeDate + '\'' +
                '}';
    }
}
