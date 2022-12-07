package com.example.gwaje3ju.entity;


import com.example.gwaje3ju.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String desc;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;


    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.desc = requestDto.getDesc();
        this.userName = requestDto.getUserName();
        this.password = requestDto.getPassword();
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.desc = requestDto.getDesc();
        this.userName = requestDto.getUserName();
        this.password = requestDto.getPassword();
    }
}