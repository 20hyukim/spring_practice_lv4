package com.sparta.lv3project.controller;

import com.sparta.lv3project.dto.Instructor.InstructorResponseDto;
import com.sparta.lv3project.dto.Instructor.InstructorSignupRequestDto;
import com.sparta.lv3project.dto.Instructor.InstructorUpdateRequestDto;
import com.sparta.lv3project.dto.Lecture.LectureResponseDto;
import com.sparta.lv3project.entity.Instructor.Instructor;
import com.sparta.lv3project.service.InstructorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/instructor")
public class InstructorController {

    private final InstructorService instructorService;


    @PostMapping("/")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<?> signup(@Valid @RequestBody InstructorSignupRequestDto requestDto) {
        System.out.println(requestDto.getCompany());

        return instructorService.signup(requestDto);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<InstructorUpdateRequestDto> updateInstructor(@PathVariable Long id, @Valid @RequestBody InstructorUpdateRequestDto requestDto) {
        InstructorUpdateRequestDto updatedInstructorDto = instructorService.updateInstructor(id, requestDto);
        return ResponseEntity.ok(updatedInstructorDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<?> viewInstructor(@PathVariable Long id) {
        return instructorService.viewInstructor(id);
    }

    @GetMapping("/lecture/{name}")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public @ResponseBody List<LectureResponseDto> selectedInstructorLectures (@PathVariable String name) {
        return instructorService.selectedInstructorLectures(name);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<InstructorResponseDto> deleteInstructor(@PathVariable Long id){
        return instructorService.deleteInstructor(id);
    }

}
