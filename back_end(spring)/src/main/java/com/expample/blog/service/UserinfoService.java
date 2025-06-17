package com.expample.blog.service;

import com.expample.blog.repository.UserinfoRepository;
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

    public boolean matches(String rawPw, String savedPw) {
        String encodedPw = encodePassword(rawPw);
        return (encodedPw.equals(savedPw));
    }
}
