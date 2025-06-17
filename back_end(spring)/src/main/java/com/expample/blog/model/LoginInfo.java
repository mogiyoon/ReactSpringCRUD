package com.expample.blog.model;

import jakarta.persistence.*;

@Entity
@Table(name = "userinfo")
public class LoginInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    // 기본 생성자
    public LoginInfo() {}

    // 생성자
    public LoginInfo(String firstName, String lastName, String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
