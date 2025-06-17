package com.expample.blog.controller;

import com.expample.blog.model.LoginInfo;
import com.expample.blog.model.Userinfo;
import com.expample.blog.repository.UserinfoRepository;
import com.expample.blog.service.UserinfoService;
import com.expample.blog.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginInfoController {
    private static final Logger log = LoggerFactory.getLogger(LoginInfoController.class);

    private final UserinfoRepository repo;
    private final UserinfoService userServ;

    public LoginInfoController(UserinfoRepository repo, UserinfoService userServ) {
        this.repo = repo;
        this.userServ = userServ;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginInfo req) {
        Userinfo user = repo.findByEmail(req.getEmail());
        if (user != null && userServ.matches(req.getPassword(), user.getPassword())) {
            String jwt = JwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(Map.of("token", jwt));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
