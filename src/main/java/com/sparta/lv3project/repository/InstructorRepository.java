package com.sparta.lv3project.repository;

import com.sparta.lv3project.entity.Instructor.Instructor;
import com.sparta.lv3project.entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorRepository extends JpaRepository <Instructor, Long> {
    Optional<Object> findByName(String name);
}
