package com.expample.blog.repository;

import com.expample.blog.model.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserinfoRepository extends JpaRepository<Userinfo, Long> {
    Userinfo findByEmail(String email);
}
