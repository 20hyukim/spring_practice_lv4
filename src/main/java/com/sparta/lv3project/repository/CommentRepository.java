package com.sparta.lv3project.repository;

import com.sparta.lv3project.entity.Like.Like;
import com.sparta.lv3project.entity.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository <Comment, Long> {

    List<Comment> findByLecture_LectureId(Long id);

    Optional<Like> findByLecture_LectureIdAndUser_UserId(Long lectureId, Long userId);
}
