package com.expample.blog.controller;

import com.expample.blog.model.Userinfo;
import com.expample.blog.repository.UserinfoRepository;
import com.expample.blog.service.UserinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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

    @GetMapping
    public ResponseEntity<?> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            log.info(String.valueOf(authentication == null));
            log.info(String.valueOf(!Objects.requireNonNull(authentication).isAuthenticated()));
            log.info(String.valueOf("anonymousUser".equals(authentication.getPrincipal())));

            return ResponseEntity.status(401).body("User not authenticated.");
        }

        String userEmail = (String) authentication.getPrincipal(); // Assuming principal is the email

        Optional<Userinfo> userOptional = Optional.ofNullable(repo.findByEmail(userEmail));

        if (userOptional.isPresent()) {
            Userinfo user = userOptional.get();
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("email", user.getEmail());

            return ResponseEntity.ok(userInfo); // 200 OK 응답과 함께 사용자 정보 반환
        } else {
            // 토큰은 유효했지만, DB에서 사용자를 찾을 수 없는 경우 (이상 상황)
            return ResponseEntity.status(404).body("User not found in database.");
        }
    }

    @PostMapping
    public Userinfo createUserinfo(@RequestBody Userinfo userinfo) {
        userinfo.setPassword(userServ.encodePassword(userinfo.getPassword()));
        return repo.save(userinfo);
    }
}
