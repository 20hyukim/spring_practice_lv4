package com.sparta.lv3project.dto;

import com.sparta.lv3project.entity.Instructor.Instructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InstructorUpdateRequestDto {
    private String company;
    private int year;
    private Long tel;
    private String intro;

    public InstructorUpdateRequestDto(Instructor instructor) {
        this.company = instructor.getCompany();
        this.year = instructor.getYear();
        this.tel = instructor.getTel();
        this.intro = instructor.getIntro();
    }
}
