package com.example.gwaje3ju.controller;

import com.example.gwaje3ju.dto.LoginRequestDto;
import com.example.gwaje3ju.dto.ResponseDto;
import com.example.gwaje3ju.dto.SignupRequestDto;
import com.example.gwaje3ju.entity.User;
import com.example.gwaje3ju.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity signup(@Valid @RequestBody SignupRequestDto signupRequestDto, Errors errors) {
        if(errors.hasErrors()) {
        Map<String, String> validatorResult = userService.validateHandling(errors);

            return ResponseEntity.ok(validatorResult);
        }
        userService.signup(signupRequestDto);
        String msg = "회원가입에 성공하였습니다.";
        return ResponseEntity.ok(new ResponseDto(msg, HttpStatus.OK.value()));
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return "success";
    }
}