package com.sparta.lv3project.service;

import com.sparta.lv3project.dto.Instructor.InstructorResponseDto;
import com.sparta.lv3project.dto.Instructor.InstructorSignupRequestDto;
import com.sparta.lv3project.dto.Instructor.InstructorUpdateRequestDto;
import com.sparta.lv3project.dto.Lecture.LectureResponseDto;
import com.sparta.lv3project.dto.Lecture.LectureSignupRequestDto;
import com.sparta.lv3project.dto.Lecture.LectureUpdateRequestDto;
import com.sparta.lv3project.dto.comment.CommentResponseDto;
import com.sparta.lv3project.entity.Instructor.Instructor;
import com.sparta.lv3project.entity.Lecture.Lecture;
import com.sparta.lv3project.entity.Lecture.LectureCategoryEnum;
import com.sparta.lv3project.entity.comment.Comment;
import com.sparta.lv3project.repository.CommentRepository;
import com.sparta.lv3project.repository.LectureRepository;
import com.sparta.lv3project.repository.LikeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;


    @Transactional
    public ResponseEntity<?> signup(LectureSignupRequestDto requestDto) {

        Lecture lecture = new Lecture(requestDto);
        Lecture savedLecture = lectureRepository.save(lecture);
        return ResponseEntity.ok(lecture);
    }

    @Transactional
    public LectureUpdateRequestDto updateLecture(Long id, LectureUpdateRequestDto requestDto) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("강의를 찾을 수 없습니다."));

        return new LectureUpdateRequestDto(lecture);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> viewLecture(Long id) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("강의를 찾을 수 없습니다."));
        LectureResponseDto responseDto = new LectureResponseDto(lecture);
        long likes = likeRepository.countByLecture_LectureId(id); //like 추가하기 위해, responseDto에 likes 추가.
        List<Comment> comments = commentRepository.findByLecture_LectureId(id);

        responseDto.setLikes(likes);

        List<CommentResponseDto> commentDtos = comments.stream()
                .map(CommentResponseDto::new)
                .toList();
        responseDto.setComments(commentDtos);


        return ResponseEntity.ok(responseDto);

    }

    @Transactional(readOnly = true)
    public List<LectureResponseDto> viewLectures(LectureCategoryEnum category) {
        List<Lecture> lectures = lectureRepository.findByCategory(category);

        return lectures.stream()
                .sorted(Comparator.comparing(Lecture::getRegistrationDate).reversed())
                .map(LectureResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity<LectureResponseDto> deleteLecture(Long id) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("강의를 찾을 수 없습니다."));
        lectureRepository.deleteById(id);
        return ResponseEntity.ok(new LectureResponseDto(lecture));
    }

}
