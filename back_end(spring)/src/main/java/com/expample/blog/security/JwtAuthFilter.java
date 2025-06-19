package com.expample.blog.security;

import com.expample.blog.util.JwtUtil; // JwtUtil 임포트
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService; // UserDetailsService 임포트
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // 이 클래스를 스프링 빈으로 등록
@RequiredArgsConstructor // Lombok을 사용하여 UserDetailsService를 주입받음
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService; // 위에서 만든 UserinfoDetailsService 빈이 주입됩니다.

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = null;
        // 1. 요청에서 "jwtToken" 이름의 HttpOnly 쿠키 찾기
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwtToken".equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    break;
                }
            }
        }

        // 2. JWT가 존재하고 유효한지 검증 (JwtUtil에 validateToken, extractEmail 메서드 필요)
        if (jwt != null && JwtUtil.validateToken(jwt)) {
            String userEmail = JwtUtil.extractEmail(jwt); // JWT에서 사용자 이메일 추출

            // 현재 SecurityContextHolder에 이미 인증 정보가 없는 경우에만 처리 (중복 인증 방지)
            // (이메일이 null이 아닌지도 추가로 체크)
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 3. UserDetailsService를 통해 사용자 상세 정보(UserDetails) 로드
                //    여기서 loadUserByUsername이 UsernameNotFoundException을 던질 수 있으므로 try-catch 고려
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

                // 4. UserDetails를 기반으로 Authentication 객체 생성
                //    첫 번째 인자는 principal (사용자 이름/ID), 세 번째 인자는 권한 목록
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(), null, userDetails.getAuthorities());
                // Password (credentials)는 이미 JWT 검증 과정에서 확인되었으므로 null로 둡니다.

                // 5. SecurityContextHolder에 Authentication 객체 설정
                //    이 과정이 성공하면, 이후의 스프링 시큐리티 필터나 컨트롤러에서
                //    사용자 인증 상태를 '인증됨'으로 인식합니다.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 다음 필터로 요청을 전달
        filterChain.doFilter(request, response);
    }
}
