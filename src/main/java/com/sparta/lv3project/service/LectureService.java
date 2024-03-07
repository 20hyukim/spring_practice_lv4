package com.sparta.lv3project.service;

import com.sparta.lv3project.dto.Instructor.InstructorSignupRequestDto;
import com.sparta.lv3project.dto.Lecture.LectureSignupRequestDto;
import com.sparta.lv3project.entity.Instructor.Instructor;
import com.sparta.lv3project.entity.Lecture.Lecture;
import com.sparta.lv3project.repository.LectureRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
