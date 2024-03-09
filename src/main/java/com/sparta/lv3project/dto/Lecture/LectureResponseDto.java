package com.sparta.lv3project.dto.Lecture;

import com.sparta.lv3project.dto.comment.CommentResponseDto;
import com.sparta.lv3project.entity.Lecture.Lecture;
import com.sparta.lv3project.entity.Lecture.LectureCategoryEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LectureResponseDto {
    private String lectureName;
    private Long price;
    private String intro;
    private LectureCategoryEnum category;
    private String username;
    private Long likes;
    @Setter
    private List<CommentResponseDto> comments;

    public LectureResponseDto(Lecture lecture){
        this.lectureName = lecture.getLectureName();
        this.price = lecture.getPrice();
        this.intro = lecture.getIntro();
        this.category = lecture.getCategory();
        this.username = lecture.getUsername();

    }


    public void setLikes(long likes) {
        this.likes = likes;
    }

}
