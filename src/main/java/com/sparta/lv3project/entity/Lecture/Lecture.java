package com.sparta.lv3project.entity.Lecture;

import com.sparta.lv3project.dto.Lecture.LectureSignupRequestDto;
import com.sparta.lv3project.entity.Instructor.Instructor;
import com.sparta.lv3project.entity.Like.Like;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "lecture")
    private List<Like> lectureLike = new ArrayList<>();

    public Lecture(LectureSignupRequestDto requestDto) {
        this.lectureName = requestDto.getLectureName();
        this.price = requestDto.getPrice();
        this.intro = requestDto.getIntro();
        this.category = requestDto.getCategory();
        this.username = requestDto.getUsername();
    }
}
