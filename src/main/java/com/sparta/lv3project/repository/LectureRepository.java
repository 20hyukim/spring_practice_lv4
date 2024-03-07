package com.sparta.lv3project.repository;

import com.sparta.lv3project.entity.Lecture.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
