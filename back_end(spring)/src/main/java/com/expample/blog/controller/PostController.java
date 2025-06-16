package com.expample.blog.controller;

import com.expample.blog.model.Post;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class PostController {

    @GetMapping("/posts")
    public List<Post> getPosts() {
        return List.of(
                new Post(
                        "me",
                        "long")
        );
    }
}
