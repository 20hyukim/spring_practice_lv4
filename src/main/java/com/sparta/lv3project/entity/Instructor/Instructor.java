package com.sparta.lv3project.entity.Instructor;

import com.sparta.lv3project.dto.Instructor.InstructorSignupRequestDto;
import com.sparta.lv3project.entity.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "instructors")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private String name;

    @Column(nullable = false)
    private int year;

    @Column
    private String company;

    @Column
    private Long tel;

    @Column
    private String intro;


    public Instructor(InstructorSignupRequestDto requestDto) {
        this.name = requestDto.getName();
        this.year = requestDto.getYear();
        this.company = requestDto.getCompany();
        this.tel = requestDto.getTel();
        this.intro = requestDto.getIntro();
    }
}
