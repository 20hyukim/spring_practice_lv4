package com.sparta.lv3project.entity.Instructor;

import com.sparta.lv3project.entity.User.User;
import com.sparta.lv3project.entity.User.UserDepartmentEnum;
import com.sparta.lv3project.entity.User.UserRoleEnum;
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
    @OneToOne
    @JoinColumn(name = "user_userId")
    private User userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int year;

    @Column
    private String company;

    @Column
    private Long tel;

    @Column
    private String intro;


}
