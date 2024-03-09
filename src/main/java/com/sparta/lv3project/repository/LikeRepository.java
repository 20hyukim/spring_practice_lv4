package com.sparta.lv3project.repository;

import com.sparta.lv3project.entity.Like.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository <Like, Long> {
    Optional<Like> findByLecture_LectureIdAndUser_UserId(Long lectureId, Long userId);

    long countByLecture_LectureId(Long id);
}
