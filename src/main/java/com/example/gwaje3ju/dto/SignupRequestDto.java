package com.example.gwaje3ju.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {



    @NotBlank(message = "유저이름을 입력해주세요.")
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "유저이름은 알파벳 소문자, 숫자로 이루어진 4자 이상, 10자 이하로 설정해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$", message = "패스워드는 알파벳 소문자, 대문자, 숫자로 이루어진 8자 이상, 15자 이하로 설정해주세요.")
    private String password;

    @NotBlank(message = "올바른 이메일 형식이 아닙니다.")
    @Email
    private String email;
}