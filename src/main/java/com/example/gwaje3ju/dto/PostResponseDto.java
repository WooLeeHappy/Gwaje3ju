package com.example.gwaje3ju.dto;

import com.example.gwaje3ju.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
public class PostResponseDto {

    private Long id;
    private String title;
    private String desc;
    private String userName;
    private String password;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.desc = post.getDesc();
        this.userName = post.getUserName();
        this.id = post.getId();
        this.password = post.getPassword();
    }
}