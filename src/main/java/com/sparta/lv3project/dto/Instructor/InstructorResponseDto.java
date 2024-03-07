package com.sparta.lv3project.dto.Instructor;

import com.sparta.lv3project.entity.Instructor.Instructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InstructorResponseDto {
    private String name;
    private int year;
    private String company;
    private Long tel;
    private String intro;

    public InstructorResponseDto(Instructor instructor) {
        this.name = instructor.getName();
        this.year = instructor.getYear();
        this.company = instructor.getCompany();
        this.tel = instructor.getTel();
        this.intro = instructor.getIntro();
    }
}
