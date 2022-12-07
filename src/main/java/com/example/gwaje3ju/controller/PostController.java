package com.example.gwaje3ju.controller;


import com.example.gwaje3ju.dto.PostPasswordDto;
import com.example.gwaje3ju.dto.PostRequestDto;
import com.example.gwaje3ju.dto.PostResponseDto;
import com.example.gwaje3ju.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @PostMapping("/post")
    public PostResponseDto savePost(@RequestBody PostRequestDto requestDto) {
        return postService.savepost(requestDto);
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
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }

    @DeleteMapping("/post/{id}")
    public boolean delete(@PathVariable Long id, @RequestBody PostPasswordDto passwordDto) {
        return postService.delete(id, passwordDto);
    }

}