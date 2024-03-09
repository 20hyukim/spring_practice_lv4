package com.sparta.lv3project.controller;

import com.sparta.lv3project.security.UserDetailsImpl;
import com.sparta.lv3project.security.UserDetailsServiceImpl;
import com.sparta.lv3project.service.LectureService;
import com.sparta.lv3project.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/like")
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PutMapping("/{lectureId}") // id = 선택한 강의
    @PreAuthorize("hasAuthority('ROLE_MANAGER') or hasAuthority('ROLE_USER')")
    public ResponseEntity<?> selectedLike(@PathVariable Long lectureId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getUserId();// 에러가 안나는지 이 부분 확인 *****
        return likeService.toggleLike(lectureId, userId);
    }
}
