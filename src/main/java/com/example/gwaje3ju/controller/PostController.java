package com.example.gwaje3ju.controller;


import com.example.gwaje3ju.dto.PostRequestDto;
import com.example.gwaje3ju.dto.PostResponseDto;
import com.example.gwaje3ju.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 포스트 생성하기
    @PostMapping("/posting")
    public PostResponseDto savePost(@RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.savepost(requestDto, request);
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.updatePost(id, requestDto, request);
    }

    @DeleteMapping("/post/{id}")
    public boolean delete(@PathVariable Long id, HttpServletRequest request) {
        return postService.delete(id, request);
    }

}