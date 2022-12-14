package com.example.gwaje3ju.entity;


import com.example.gwaje3ju.dto.PostRequestDto;
import lombok.AllArgsConstructor;
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



    @ManyToOne
    @JoinColumn
    private User user;

    public Post(PostRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.desc = requestDto.getDesc();

        this.user = user;
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.desc = requestDto.getDesc();

    }
}
