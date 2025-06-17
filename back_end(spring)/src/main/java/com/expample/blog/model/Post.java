package com.expample.blog.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "posts")
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String title;
    @Setter
    private String contents;

    public Post() {}

    public Post(String title, String contents) {
        setTitle(title);
        setContents(contents);
    }

}
