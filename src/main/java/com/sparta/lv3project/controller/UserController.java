package com.sparta.lv3project.controller;

import com.sparta.lv3project.dto.User.SignupRequestDto;
import com.sparta.lv3project.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) { //하나도 없으면 에러가 안난거니까.
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body("관리자 가입 실패");
        }
        System.out.println(requestDto.getDepartment());

        userService.signup(requestDto);

        return ResponseEntity.ok().body("관리자 가입 성공");
    }


}
