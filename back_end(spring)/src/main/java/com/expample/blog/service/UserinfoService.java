package com.expample.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserinfoService {
    private final PasswordEncoder passwordEncoder;

    public String encodePassword(String rawPw) {
        return passwordEncoder.encode(rawPw);
    }

    public boolean matchesPassword(String rawPw, String savedPw) {
        return passwordEncoder.matches(rawPw, savedPw);
    }
}
