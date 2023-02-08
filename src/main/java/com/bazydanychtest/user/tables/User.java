package com.bazydanychtest.user.tables;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false, unique = true, length = 45)
    private String email;
    @Column
    private String sex;
    @Column( nullable = false)
    private String password;
    @Column
    private String role;
    @Column
    private String path;
    @Column
    private String createDate;
    @Column
    private String lastVisit;

    public User() {
    }

    public User(String userName,  String email, String sex, String password, String role, String createDate, String path) {
        this.userName = userName;
        this.email = email;
        this.sex = sex;
        this.password = password;
        this.role = role;
        this.createDate = createDate;
        this.path = path;
    }

    public User(Integer id, String userName, String email, String sex, String password, String role, String path, String createDate, String lastVisit) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.sex = sex;
        this.password = password;
        this.role = role;
        this.path = path;
        this.createDate = createDate;
        this.lastVisit = lastVisit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(String lastVisit) {
        this.lastVisit = lastVisit;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", path='" + path + '\'' +
                ", createDate='" + createDate + '\'' +
                ", lastVisit='" + lastVisit + '\'' +
                '}';
    }
}
