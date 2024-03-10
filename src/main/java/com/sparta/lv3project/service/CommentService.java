package com.sparta.lv3project.service;

import com.sparta.lv3project.dto.comment.CommentEditRequestDto;
import com.sparta.lv3project.dto.comment.CommentRequestDto;
import com.sparta.lv3project.entity.Like.Like;
import com.sparta.lv3project.entity.comment.Comment;
import com.sparta.lv3project.repository.CommentRepository;
import com.sparta.lv3project.repository.LectureRepository;
import com.sparta.lv3project.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<?> createComment(CommentRequestDto requestDto, Long userId) {
        Comment saveComment = new Comment(requestDto.getComment());
        saveComment.setLecture(lectureRepository.findById(requestDto.getLectureId()).orElseThrow(() -> new IllegalArgumentException("해당 강의는 없습니다.")));
        saveComment.setUser(userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다.")));

        commentRepository.save(saveComment);

        return ResponseEntity.ok("댓글이 생성되었습니다.");
    }

    @Transactional
    public ResponseEntity<?> editComment(CommentEditRequestDto requestDto, Long userId) {
        Long commentId = requestDto.getCommentId();
        Optional<Comment> existingComment = commentRepository.findById(commentId);

        if(existingComment.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("댓글을 찾을 수 없습니다.");
        }

        Comment comment = commentRepository.findById(commentId).get();
        if (!comment.getUser().getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("이 댓글을 수정할 권한이 없습니다.");
        }

        comment.setComment(requestDto.getComment());
        commentRepository.save(comment);

        return ResponseEntity.ok("댓글이 성공적으로 수정되었습니다.");
    }

    @Transactional
    public ResponseEntity<?> deleteComment(Long commentId, Long userId) {
        Optional<Comment> existingComment = commentRepository.findById(commentId);

        if(existingComment.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("댓글을 찾을 수 없습니다.");
        }

        Comment comment = commentRepository.findById(commentId).get();
        if (!comment.getUser().getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("이 댓글을 삭제할 권한이 없습니다.");
        }

        commentRepository.deleteById(commentId);
        return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
    }
}
