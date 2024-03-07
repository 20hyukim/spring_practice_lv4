package com.sparta.lv3project.dto.Lecture;

import com.sparta.lv3project.entity.Lecture.LectureCategoryEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LectureSignupRequestDto {
    private String lectureName;
    private Long price;
    private String intro;
    private LectureCategoryEnum category;
    private String username;
}
