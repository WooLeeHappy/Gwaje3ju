package com.example.gwaje3ju.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseDto {
    private String msg;

    private int statuscode;

    public ResponseDto(String msg, int statuscode) {
        this.msg = msg;
        this.statuscode = statuscode;
    }
}
