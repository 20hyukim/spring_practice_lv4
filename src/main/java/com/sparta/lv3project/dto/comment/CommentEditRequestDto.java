package com.sparta.lv3project.dto.comment;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentEditRequestDto {
    private Long commentId;
    private String comment;

}
