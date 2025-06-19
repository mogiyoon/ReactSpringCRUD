package com.expample.blog.service;

import com.expample.blog.model.Userinfo;
import com.expample.blog.repository.UserinfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service // 이 클래스를 스프링 빈으로 등록
@RequiredArgsConstructor // UserinfoRepository를 주입받기 위함
public class UserinfoDetailsService implements UserDetailsService {

    private final UserinfoRepository userinfoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. 제공된 이메일(username)로 데이터베이스에서 사용자 정보 조회
        //    (앞서 UserinfoRepository에 findByEmail 메서드가 있다고 가정했습니다.)

        Userinfo userinfo = userinfoRepository.findByEmail(email);

        // 2. 조회된 사용자 정보를 바탕으로 스프링 시큐리티의 UserDetails 객체 생성
        return new User(
                userinfo.getEmail(),          // principal (사용자 이름/ID)
                userinfo.getPassword(),       // credentials (비밀번호, 이미 암호화됨)
                Collections.emptyList()       // authorities (사용자 권한 목록. 일단 비워둡니다.)
                // Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")) // 예시: 단일 ROLE_USER 부여
        );
    }
}
