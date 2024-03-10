package com.sparta.lv3project.service;

import com.sparta.lv3project.dto.Instructor.InstructorResponseDto;
import com.sparta.lv3project.dto.Instructor.InstructorSignupRequestDto;
import com.sparta.lv3project.dto.Instructor.InstructorUpdateRequestDto;
import com.sparta.lv3project.dto.Lecture.LectureResponseDto;
import com.sparta.lv3project.entity.Instructor.Instructor;
import com.sparta.lv3project.entity.Lecture.Lecture;
import com.sparta.lv3project.entity.User.User;
import com.sparta.lv3project.entity.User.UserRoleEnum;
import com.sparta.lv3project.repository.InstructorRepository;
import com.sparta.lv3project.repository.LectureRepository;
import com.sparta.lv3project.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class InstructorService {

    private final UserRepository userRepository;
    private final InstructorRepository instructorRepository;
    private final LectureRepository lectureRepository;

    public InstructorService(UserRepository userRepository, InstructorRepository instructorRepository, LectureRepository lectureRepository) {
        this.userRepository = userRepository;
        this.instructorRepository = instructorRepository;
        this.lectureRepository = lectureRepository;
    }


    @Transactional
    public ResponseEntity<?> signup(InstructorSignupRequestDto requestDto) {

        Instructor instructor = new Instructor(requestDto);
        Instructor savedInstructor = instructorRepository.save(instructor);
        return ResponseEntity.ok(new InstructorSignupRequestDto(savedInstructor));

    }

    @Transactional
    public InstructorUpdateRequestDto updateInstructor(Long id, InstructorUpdateRequestDto requestDto) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("강사를 찾을 수 없습니다."));

        return new InstructorUpdateRequestDto(instructor);

    }

    public ResponseEntity<?> viewInstructor(Long id) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("강사를 찾을 수 없습니다."));
        InstructorResponseDto responseDto = new InstructorResponseDto(instructor);
        return ResponseEntity.ok(responseDto);
    }

    public List<LectureResponseDto> selectedInstructorLectures(String name) {
        Object instructor = instructorRepository.findByName(name).orElseThrow(() -> new IllegalArgumentException("해당 강사가 존재하지 않습니다."));

        List<Lecture> lectures = lectureRepository.findByUsername(name);

        return lectures.stream()
                .sorted(Comparator.comparing(Lecture::getRegistrationDate).reversed())
                .map(LectureResponseDto::new).collect(Collectors.toList());
    }

    public ResponseEntity<InstructorResponseDto> deleteInstructor(Long id) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("강사 ID" + id + "를 찾을 수 없습니다."));

        instructorRepository.deleteById(id);
        return ResponseEntity.ok(new InstructorResponseDto(instructor));
    }
}
