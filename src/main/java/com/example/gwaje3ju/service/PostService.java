package com.example.gwaje3ju.service;


import com.example.gwaje3ju.dto.PostPasswordDto;
import com.example.gwaje3ju.dto.PostRequestDto;
import com.example.gwaje3ju.dto.PostResponseDto;
import com.example.gwaje3ju.entity.Post;
import com.example.gwaje3ju.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {


    private final PostRepository postRepository;

    @Transactional
    public PostResponseDto savepost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);

        postRepository.save(post);
        return new PostResponseDto(post);
    }
    @Transactional
    public List<PostResponseDto> getPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostResponseDto> lists = new ArrayList<>();
        for (Post post : posts) {
            PostResponseDto postResponseDto = PostResponseDto.builder()
                    .id(post.getId())
                    .desc(post.getDesc())
                    .userName(post.getUserName())
                    .password(post.getPassword())
                    .title(post.getTitle())
                    .build();
            lists.add(postResponseDto);
        }
        return lists;
    }

    @Transactional
    public PostResponseDto getPost(Long id) {
        Optional<Post> a = postRepository.findById(id);
        Post v = a.get();
        PostResponseDto postResponseDto = new PostResponseDto(v);
        return postResponseDto;
    }


    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto) {
        Post post = postRepository.findByIdAndPassword(id, postRequestDto.getPassword()).orElseThrow(
                () -> new RuntimeException("ㅜㅜ"));
        post.update(postRequestDto);
        PostResponseDto responseDto = new PostResponseDto(post);
        return responseDto;
    }

    @Transactional
    public boolean delete(Long id, PostPasswordDto passwordDto) {
        Post post = postRepository.findByIdAndPassword(id, passwordDto.getPassword()).orElseThrow(
                () -> new RuntimeException("ㅠㅠ"));
        postRepository.deleteById(id);
        return true;

    }

}
