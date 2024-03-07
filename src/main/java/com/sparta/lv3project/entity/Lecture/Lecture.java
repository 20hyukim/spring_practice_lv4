package com.sparta.lv3project.entity.Lecture;

import com.sparta.lv3project.dto.Lecture.LectureSignupRequestDto;
import com.sparta.lv3project.entity.Instructor.Instructor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "lectures")
public class Lecture extends LectureTimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    @Column
    private String lectureName;

    @Column
    private Long price;

    @Column
    private String intro;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private LectureCategoryEnum category;

    @Column
    private String username;

    public Lecture(LectureSignupRequestDto requestDto) {
        this.lectureName = requestDto.getLectureName();
        this.price = requestDto.getPrice();
        this.intro = requestDto.getIntro();
        this.category = requestDto.getCategory();
        this.username = requestDto.getUsername();
    }
}
