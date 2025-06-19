"use client";

// src/contexts/AuthContext.js
import React, { createContext, useState, useEffect, useContext, ReactNode } from 'react';
import apiClient from 'axios';

interface AuthContextValue {
  isLoggedIn: boolean;
  isLoading: boolean;
  userEmail: string | null;
  login: () => void;
  logout: () => Promise<void>; // logout 함수가 비동기이므로 Promise<void>로 타입 지정
}

const AuthContext = createContext<AuthContextValue | undefined>(undefined);

export const AuthProvider = ({ children } : {children: ReactNode}) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [isLoading, setIsLoading] = useState(true);
  const [userEmail, setUserEmail] = useState<string | null>(null); // 사용자 이메일 등 필요한 정보

  useEffect(() => {
    // 앱이 처음 로드되거나 새로고침될 때 로그인 상태 확인
    const checkLoginStatus = async () => {
      try {
        // 인증이 필요한 API (예: 현재 사용자 정보) 호출
        // HttpOnly 쿠키는 이 요청에 자동으로 포함됩니다.
        const response = await apiClient.get('http://localhost:8080/api/userinfo', {withCredentials: true});
        // 요청 성공 시: 로그인 상태로 간주
        setIsLoggedIn(true);
        setUserEmail(response.data.email); // 서버 응답에서 사용자 이메일 추출
        console.log("자동 로그인 성공: 현재 사용자:", response.data.email);
      } catch (error) {
        // 요청 실패 시: 로그인되지 않은 상태로 간주
        console.log(error);
        console.error("로그인 상태 확인 실패:", error.response ? error.response.status : error.message);
        setIsLoggedIn(false);
        setUserEmail(null);
      } finally {
        setIsLoading(false);
      }
    };

    checkLoginStatus();
  }, []);

  const login = () => setIsLoggedIn(true);
  const logout = async () => {
    try {
      // 서버의 로그아웃 엔드포인트 호출 (쿠키 삭제 요청)
      await apiClient.post('http://localhost:8080/api/logout');
      setIsLoggedIn(false);
      setUserEmail(null);
      console.log("로그아웃 성공");
    } catch (error) {
      console.error("로그아웃 실패:", error);
      // 실패해도 클라이언트 상태는 로그아웃으로 변경
      setIsLoggedIn(false);
      setUserEmail(null);
    }
  };

  return (
    <AuthContext.Provider value={{ isLoggedIn, isLoading, userEmail, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (context === undefined) { // null 대신 undefined로 체크
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};