package com.sparta.lv3project.controller;

import com.sparta.lv3project.dto.comment.CommentEditRequestDto;
import com.sparta.lv3project.dto.comment.CommentRequestDto;
import com.sparta.lv3project.security.UserDetailsImpl;
import com.sparta.lv3project.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/")
    @PreAuthorize("hasAuthority('ROLE_MANAGER') or hasAuthority('ROLE_USER')")
    public ResponseEntity<?> createComment(@Valid @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(requestDto, userDetails.getUser().getUserId());
    }

    @PatchMapping("/")
    @PreAuthorize("hasAuthority('ROLE_MANAGER') or hasAuthority('ROLE_USER')")
    public ResponseEntity<?> editComment(@Valid @RequestBody CommentEditRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.editComment(requestDto, userDetails.getUser().getUserId());
    }

    @DeleteMapping("/{commentId}")
    @PreAuthorize("hasAuthority('ROLE_MANAGER') or hasAuthority('ROLE_USER')")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(commentId, userDetails.getUser().getUserId());
    }



}
