package com.sparta.lv3project.service;

import com.sparta.lv3project.entity.Lecture.Lecture;
import com.sparta.lv3project.entity.Like.Like;
import com.sparta.lv3project.repository.LectureRepository;
import com.sparta.lv3project.repository.LikeRepository;
import com.sparta.lv3project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;


    @Transactional
    public ResponseEntity<?> toggleLike(Long lectureId, Long userId) {
        Optional<Like> existingLike = likeRepository.findByLecture_LectureIdAndUser_UserId(lectureId, userId);

        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            return ResponseEntity.ok("좋아요 취소");
        }

        Like like = new Like(); //exception-handler // builder pattern... setter, 생성자. -> 3
        like.setLecture(lectureRepository.findById(lectureId).orElseThrow());
        like.setUser(userRepository.findById(userId).orElseThrow());
        likeRepository.save(like);
        return ResponseEntity.ok("좋아요");

    }
}
