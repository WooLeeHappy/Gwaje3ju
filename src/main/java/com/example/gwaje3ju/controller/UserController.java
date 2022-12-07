package com.example.gwaje3ju.controller;

import com.example.gwaje3ju.dto.LoginRequestDto;
import com.example.gwaje3ju.dto.ResponseDto;
import com.example.gwaje3ju.dto.SignupRequestDto;
import com.example.gwaje3ju.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;


    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return "success";
    }

}