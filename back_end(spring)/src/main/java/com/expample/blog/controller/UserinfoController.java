package com.expample.blog.controller;

import com.expample.blog.model.Userinfo;
import com.expample.blog.repository.UserinfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userinfo")
@CrossOrigin(origins = "*")
public class UserinfoController {
    private static final Logger log = LoggerFactory.getLogger(UserinfoController.class);

    private final UserinfoRepository repo;

    public UserinfoController(UserinfoRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Userinfo createUserinfo(@RequestBody Userinfo userinfo) {
        return repo.save(userinfo);
    }
}
