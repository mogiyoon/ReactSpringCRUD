package com.expample.blog.controller;

import com.expample.blog.model.LoginInfo;
import com.expample.blog.model.Userinfo;
import com.expample.blog.repository.UserinfoRepository;
import com.expample.blog.service.UserinfoService;
import com.expample.blog.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginInfoController {
    private static final Logger log = LoggerFactory.getLogger(LoginInfoController.class);

    private final UserinfoRepository repo;
    private final UserinfoService userServ;

    public LoginInfoController(UserinfoRepository repo, UserinfoService userServ) {
        this.repo = repo;
        this.userServ = userServ;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginInfo req, HttpServletResponse response) {
        Userinfo user = repo.findByEmail(req.getEmail());
        if (user != null && userServ.matchesPassword(req.getPassword(), user.getPassword())) {
            String jwt = JwtUtil.generateToken(user.getEmail());

            // --- JWT를 HttpOnly 쿠키에 담는 부분 ---
            Cookie jwtCookie = new Cookie("jwtToken", jwt); // 쿠키 이름은 "jwtToken"
            jwtCookie.setHttpOnly(true); // JavaScript에서 접근 불가 (보안 강화)
            jwtCookie.setSecure(false); // HTTPS 통신에서만 전송 (운영 환경 필수)
            jwtCookie.setPath("/"); // 모든 경로에서 쿠키 접근 가능
            jwtCookie.setMaxAge(60 * 60); // 쿠키 유효 시간 (초 단위, 여기서는 1시간)
            // JWT의 만료 시간과 일치시키는 것이 좋음
            response.addCookie(jwtCookie); // 응답에 쿠키 추가

            return ResponseEntity.ok("Login Success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie jwtCookie = new Cookie("jwtToken", null); // 쿠키 값을 null로 설정
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(false); // HTTPS 사용 시
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0); // 쿠키 유효 시간을 0으로 설정하여 즉시 만료 및 삭제
        response.addCookie(jwtCookie);

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity.ok("Logout successful");
    }
}
