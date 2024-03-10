package com.sparta.lv3project.controller;

import com.sparta.lv3project.dto.Instructor.InstructorSignupRequestDto;
import com.sparta.lv3project.dto.Instructor.InstructorUpdateRequestDto;
import com.sparta.lv3project.dto.Lecture.LectureResponseDto;
import com.sparta.lv3project.dto.Lecture.LectureSignupRequestDto;
import com.sparta.lv3project.dto.Lecture.LectureUpdateRequestDto;
import com.sparta.lv3project.dto.User.SignupRequestDto;

import com.sparta.lv3project.entity.Lecture.Lecture;
import com.sparta.lv3project.entity.Lecture.LectureCategoryEnum;
import com.sparta.lv3project.security.UserDetailsImpl;
import com.sparta.lv3project.service.LectureService;
import com.sparta.lv3project.service.LikeService;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/lecture")
public class LectureController {

    private final LectureService lectureService;


    @PostMapping("/")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<?> signup(@Valid @RequestBody LectureSignupRequestDto requestDto) {
        return lectureService.signup(requestDto);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<LectureUpdateRequestDto> updateLecture(@PathVariable Long id, @Valid @RequestBody LectureUpdateRequestDto requestDto) {
        LectureUpdateRequestDto updatedLectureDto = lectureService.updateLecture(id, requestDto);
        return ResponseEntity.ok(updatedLectureDto);
    }

    @GetMapping("/{id}")//get put post delete patch method 알아보기 ******
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<?> viewLecture(@PathVariable Long id){
        return lectureService.viewLecture(id);
    }

    @GetMapping("/category/{category}")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public @ResponseBody List<LectureResponseDto> viewLectures(@PathVariable LectureCategoryEnum category) {
        return lectureService.viewLectures(category);
    }

    @DeleteMapping("/{id}") //행위 x  /{id} restfulAPI http 통신 method. ******
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<LectureResponseDto> deleteLecture(@PathVariable Long id){
        return lectureService.deleteLecture(id);
    }

    // comments


}
