package com.sparta.lv3project.Lecture;

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

    @ManyToOne
    @JoinColumn(name = "instructor_userId")
    private Instructor userId;

    @ManyToOne
    @JoinColumn(name = "instructor_name")
    private Instructor name;

}
