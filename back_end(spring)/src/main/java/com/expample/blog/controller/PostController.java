package com.expample.blog.controller;

import com.expample.blog.model.Post;
import com.expample.blog.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {
    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    private final PostRepository repo;

    public PostController(PostRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Post> getPosts() {
        List<Post> debugPost = repo.findAll();
        for (int i = 0; i < debugPost.size(); i++) {
            log.info(debugPost.get(i).getTitle());
            log.info(debugPost.get(i).getId().toString());
        }
        return debugPost;
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return repo.save(post);
    }
}
