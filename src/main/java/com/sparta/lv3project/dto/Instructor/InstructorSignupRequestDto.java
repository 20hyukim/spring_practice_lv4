package com.sparta.lv3project.dto.Instructor;

import com.sparta.lv3project.entity.Instructor.Instructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InstructorSignupRequestDto {
    private String name;
    private int year;
    private String company;
    private Long tel;
    private String intro;

    public InstructorSignupRequestDto(Instructor savedInstructor) {
        this.name = savedInstructor.getName();
        this.year = savedInstructor.getYear();
        this.company = savedInstructor.getCompany();
        this.tel = savedInstructor.getTel();
        this.intro = savedInstructor.getIntro();
    }
}
