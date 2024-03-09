package com.sparta.lv3project.controller;

import com.sparta.lv3project.dto.Instructor.InstructorSignupRequestDto;
import com.sparta.lv3project.dto.Instructor.InstructorUpdateRequestDto;
import com.sparta.lv3project.dto.Lecture.LectureResponseDto;
import com.sparta.lv3project.dto.Lecture.LectureSignupRequestDto;
import com.sparta.lv3project.dto.Lecture.LectureUpdateRequestDto;
import com.sparta.lv3project.dto.User.SignupRequestDto;
import com.sparta.lv3project.dto.comment.CommentRequestDto;
import com.sparta.lv3project.dto.comment.CommentResponseDto;
import com.sparta.lv3project.entity.Lecture.Lecture;
import com.sparta.lv3project.entity.Lecture.LectureCategoryEnum;
import com.sparta.lv3project.security.UserDetailsImpl;
import com.sparta.lv3project.service.CommentService;
import com.sparta.lv3project.service.LectureService;
import com.sparta.lv3project.service.LikeService;
import jakarta.validation.Valid;
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
@RequestMapping("/api/lecture")
public class LectureController {

    private final LectureService lectureService;
    private final CommentService commentService;



    @Autowired
    public  LectureController(LectureService lectureService, CommentService commentService){
        this.lectureService = lectureService;
        this.commentService = commentService;
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

    @PutMapping("/view/{id}")//get put post delete patch method 알아보기 ******
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<?> viewLecture(@PathVariable Long id, Model model){
        List<CommentResponseDto> comments = commentService.getComments(id);
        model.addAttribute("comments", comments);
        return lectureService.viewLecture(id);
    }

    @PutMapping("/selected/{category}")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public @ResponseBody List<LectureResponseDto> viewLectures(@PathVariable LectureCategoryEnum category) {
        return lectureService.viewLectures(category);
    }

    @PutMapping("/delete/{id}") //행위 x  /{id} restfulAPI http 통신 method. ******
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<LectureResponseDto> deleteLecture(@PathVariable Long id){
        return lectureService.deleteLecture(id);
    }

    // comments
    @PostMapping("/view/{id}/comment")
    @PreAuthorize("hasAuthority('ROLE_MANAGER') or hasAuthority('ROLE_USER')")
    public ResponseEntity<?> createComment(@PathVariable(name = "id") Long lectureId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(lectureId, userDetails.getUser().getUserId(), requestDto);
    }

    @DeleteMapping("/view/{id}/comment/{comment_id}")
    @PreAuthorize("hasAuthority('ROLE_MANAGER') or hasAuthority('ROLE_USER')")
    public ResponseEntity<?> deleteComment(@PathVariable(name = "id") Long lectureId, @PathVariable(name = "comment_id") Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(commentId, userDetails.getUser().getUserId());
    }

    @PutMapping("/view/{id}/comment/{comment_id}")
    @PreAuthorize("hasAuthority('ROLE_MANAGER') or hasAuthority('ROLE_USER')")
    public ResponseEntity<?> updateComment(@PathVariable(name = "id") Long lectureId, @PathVariable(name = "comment_id") Long commentId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.updateComment(commentId, userDetails.getUser().getUserId(), requestDto);
    }


}
