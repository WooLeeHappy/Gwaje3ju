package com.example.gwaje3ju.repository;

import com.example.gwaje3ju.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByIdAndPassword(Long id, String password);
}

// 1 Entity 2 Controller, service, repository 자바 파일만 만든다.
// (rest)컨트롤러, 서비스, 엔티티 @Controller @Service @ Entity 을 다 붙여준다.
// 3. 연결짓는 방법 1. @RequiredArgsConstructor - 컨트롤러와 서비스에만 붙인다.
// 4. 컨트롤러 안에는 private final Service , 서비스 안에는 private final repository를 넣는다.
// 5. 컨트롤러부터 필요한 메서드를 만든다. 컨트롤러 -> 서비스 -> repository

