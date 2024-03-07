package com.sparta.lv3project.service;

import com.sparta.lv3project.dto.Instructor.InstructorResponseDto;
import com.sparta.lv3project.dto.Instructor.InstructorSignupRequestDto;
import com.sparta.lv3project.dto.Instructor.InstructorUpdateRequestDto;
import com.sparta.lv3project.dto.Lecture.LectureResponseDto;
import com.sparta.lv3project.dto.Lecture.LectureSignupRequestDto;
import com.sparta.lv3project.dto.Lecture.LectureUpdateRequestDto;
import com.sparta.lv3project.entity.Instructor.Instructor;
import com.sparta.lv3project.entity.Lecture.Lecture;
import com.sparta.lv3project.entity.Lecture.LectureCategoryEnum;
import com.sparta.lv3project.repository.LectureRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureService {

    private final LectureRepository lectureRepository;

    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

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

    public ResponseEntity<?> viewLecture(Long id) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("강의를 찾을 수 없습니다."));
        LectureResponseDto responseDto = new LectureResponseDto(lecture);
        return ResponseEntity.ok(responseDto);

    }

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
