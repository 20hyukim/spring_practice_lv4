package com.sparta.lv3project.dto.comment;

import com.sparta.lv3project.entity.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {
    private Long lectureId;
    private String comment;

    public CommentResponseDto(Comment comment) {
        this.lectureId = comment.getId();
        this.comment = comment.getComment();
        // 필요한 정보를 설정하는 로직 추가
    }
}
