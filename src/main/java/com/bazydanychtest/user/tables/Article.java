package com.bazydanychtest.user.tables;

import jakarta.persistence.*;
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
    /*@Column
    @CreationTimestamp
    private Timestamp createDate;*/

/*
    @CreationTimestamp
*/
@Column
    private String createDate;


    public Article( String author, String title, Integer likes, Integer dislikes, String createDate) {
        this.author = author;
        this.title = title;
        this.likes = likes;
        this.dislikes = dislikes;
        this.createDate = createDate;
    }

    public Article() {
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


    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
