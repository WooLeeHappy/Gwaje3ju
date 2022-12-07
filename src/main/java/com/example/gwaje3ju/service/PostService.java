package com.example.gwaje3ju.service;


import com.example.gwaje3ju.dto.PostRequestDto;
import com.example.gwaje3ju.dto.PostResponseDto;
import com.example.gwaje3ju.entity.Post;
import com.example.gwaje3ju.jwt.JwtUtil;
import com.example.gwaje3ju.repository.PostRepository;
import com.example.gwaje3ju.repository.UserRepository;
import com.example.gwaje3ju.entity.User;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {


    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;



    @Transactional
    public PostResponseDto savepost(PostRequestDto requestDto, HttpServletRequest request) {

        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 추가 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 요청받은 DTO 로 DB에 저장할 객체 만들기
            Post post = postRepository.saveAndFlush(new Post(requestDto, user));

            return new PostResponseDto(post);
        } else {
            return null;
        }
    }
    @Transactional
    public List<PostResponseDto> getPosts() {

            List<PostResponseDto> list = new ArrayList<>();
            List<Post> postList = postRepository.findAllPostByModifiedAtDesc();

            for ( Post post : postList) {
                list.add(new PostResponseDto(post));
            }
            return list;

        }




    @Transactional
    public PostResponseDto getPost(Long id) {
        Optional<Post> a = postRepository.findById(id);
        Post v = a.get();
        PostResponseDto postResponseDto = new PostResponseDto(v);
        return postResponseDto;
    }


    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto, HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("해당글의 주인이 아닙니다.")
            );

            Post post = postRepository.findByIdAndUser(id, user).orElseThrow(
                    () -> new RuntimeException("게시글이 존재하지 않습니다."));
            System.out.println(post);
            post.update(postRequestDto);
            PostResponseDto responseDto = new PostResponseDto(post);
            return responseDto;
        } else {
            return null;
        }
    }


    @Transactional
    public boolean delete(Long id, HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("해당글의 주인이 아닙니다.")
            );

            postRepository.deleteById(id);

        }
        return true;
    }
}
