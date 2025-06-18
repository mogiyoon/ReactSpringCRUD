package com.expample.blog.controller;

import com.expample.blog.model.Userinfo;
import com.expample.blog.repository.UserinfoRepository;
import com.expample.blog.service.UserinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userinfo")
public class UserinfoController {
    private static final Logger log = LoggerFactory.getLogger(UserinfoController.class);

    private final UserinfoRepository repo;
    private final UserinfoService userServ;

    public UserinfoController(UserinfoRepository repo, UserinfoService userServ) {
        this.repo = repo;
        this.userServ = userServ;
    }

    @PostMapping
    public Userinfo createUserinfo(@RequestBody Userinfo userinfo) {
        userinfo.setPassword(userServ.encodePassword(userinfo.getPassword()));
        return repo.save(userinfo);
    }
}
