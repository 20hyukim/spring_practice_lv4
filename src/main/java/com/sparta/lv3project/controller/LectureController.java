package com.sparta.lv3project.controller;

import com.sparta.lv3project.dto.Instructor.InstructorSignupRequestDto;
import com.sparta.lv3project.dto.Instructor.InstructorUpdateRequestDto;
import com.sparta.lv3project.dto.Lecture.LectureResponseDto;
import com.sparta.lv3project.dto.Lecture.LectureSignupRequestDto;
import com.sparta.lv3project.dto.Lecture.LectureUpdateRequestDto;
import com.sparta.lv3project.dto.User.SignupRequestDto;
import com.sparta.lv3project.entity.Lecture.LectureCategoryEnum;
import com.sparta.lv3project.service.LectureService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<LectureUpdateRequestDto> updateLecture(@PathVariable Long id, @Valid @RequestBody LectureUpdateRequestDto requestDto) {
        LectureUpdateRequestDto updatedLectureDto = lectureService.updateLecture(id, requestDto);
        return ResponseEntity.ok(updatedLectureDto);
    }

    @PutMapping("/view/{id}")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<?> viewLecture(@PathVariable Long id) {
        return lectureService.viewLecture(id);
    }

    @PutMapping("/selected/{category}")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public @ResponseBody List<LectureResponseDto> viewLectures(@PathVariable LectureCategoryEnum category) {
        return lectureService.viewLectures(category);
    }


}
