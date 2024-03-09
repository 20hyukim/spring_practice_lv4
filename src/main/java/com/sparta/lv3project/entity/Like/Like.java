package com.sparta.lv3project.entity.Like;

import com.sparta.lv3project.entity.Lecture.Lecture;
import com.sparta.lv3project.entity.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "lecture_likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne//patch type -> loading을 어느시점에 하냐. """""n+1 문제 야기.
    @JoinColumn(name = "user_id")
    private User user;
}
