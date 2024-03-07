package com.sparta.lv3project.controller;

import com.sparta.lv3project.dto.Instructor.InstructorSignupRequestDto;
import com.sparta.lv3project.dto.Lecture.LectureSignupRequestDto;
import com.sparta.lv3project.dto.User.SignupRequestDto;
import com.sparta.lv3project.service.LectureService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/lecture")
public class LectureController {

    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @PostMapping("/signup")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<?> signup(@Valid @RequestBody LectureSignupRequestDto requestDto) {
        return lectureService.signup(requestDto);
    }


}
