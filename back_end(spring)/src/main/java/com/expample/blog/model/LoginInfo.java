package com.expample.blog.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "userinfo")
public class LoginInfo {
    // Getter & Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;

    // 기본 생성자
    public LoginInfo() {}

    // 생성자
    public LoginInfo(String firstName, String lastName, String email, String password) {
        setEmail(email);
        setPassword(password);
    }
}
