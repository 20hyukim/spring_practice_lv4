package com.sparta.lv3project.entity.comment;

import com.sparta.lv3project.entity.Lecture.Lecture;
import com.sparta.lv3project.entity.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment")
@RequiredArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String comment;


    public Comment(String comment) {
        this.comment = comment;
    }
}
