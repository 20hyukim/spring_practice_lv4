package com.sparta.lv3project.entity.User;

import com.sparta.lv3project.entity.Like.Like;
import com.sparta.lv3project.entity.comment.Comment;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserDepartmentEnum department;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(mappedBy="user")
    private List<Like> userLike = new ArrayList<>();

    @OneToMany(mappedBy="user")
    private List<Comment> userComments = new ArrayList<>();


    public User(String username, String email, String password, UserRoleEnum role, UserDepartmentEnum department) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.department = department;
    }
}
