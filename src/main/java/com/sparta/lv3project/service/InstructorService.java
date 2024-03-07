package com.sparta.lv3project.service;

import com.sparta.lv3project.dto.InstructorSignupRequestDto;
import com.sparta.lv3project.dto.InstructorUpdateRequestDto;
import com.sparta.lv3project.entity.Instructor.Instructor;
import com.sparta.lv3project.entity.User.User;
import com.sparta.lv3project.entity.User.UserRoleEnum;
import com.sparta.lv3project.repository.InstructorRepository;
import com.sparta.lv3project.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstructorService {

    private final UserRepository userRepository;
    private final InstructorRepository instructorRepository;

    public InstructorService(UserRepository userRepository, InstructorRepository instructorRepository) {
        this.userRepository = userRepository;
        this.instructorRepository = instructorRepository;
    }


    @Transactional
    public ResponseEntity<?> signup(InstructorSignupRequestDto requestDto) {
        User user = userRepository.findByUsername(requestDto.getName()).get();

        if(!user.getRole().equals(UserRoleEnum.MANAGER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("관리자 권한이 없습니다.");
        }
        Instructor instructor = new Instructor(requestDto, user);
        Instructor savedInstructor = instructorRepository.save(instructor);
        return ResponseEntity.ok(new InstructorSignupRequestDto(savedInstructor));

    }

    @Transactional
    public InstructorUpdateRequestDto updateInstructor(Long id, InstructorUpdateRequestDto requestDto) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("강사를 찾을 수 없습니다."));

        return new InstructorUpdateRequestDto(instructor);

    }
}
