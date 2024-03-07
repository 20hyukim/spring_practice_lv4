package com.sparta.lv3project.repository;

import com.sparta.lv3project.entity.Instructor.Instructor;
import com.sparta.lv3project.entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository <Instructor, User> {
}
