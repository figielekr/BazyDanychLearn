package com.bazydanychtest.user.tables;

import jakarta.persistence.*;

@Entity
@Table(name = "comment_likes")
public class CommentLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Integer id;
    @Column(nullable = false)
    int commentID;
    @Column(nullable = false)
    String username;
    @Column(nullable = false)
    String LikeDate;

    public CommentLikes() {
    }



    public CommentLikes(int commentID, String username, String likeDate) {
        this.commentID = commentID;
        this.username = username;
        LikeDate = likeDate;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        commentID = commentID;
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
                "CommentID=" + commentID +
                ", username='" + username + '\'' +
                ", LikeDate='" + LikeDate + '\'' +
                '}';
    }
}
