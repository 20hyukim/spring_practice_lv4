package com.sparta.lv3project.dto;

import com.sparta.lv3project.entity.User.UserDepartmentEnum;
import com.sparta.lv3project.entity.User.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    private UserDepartmentEnum department;

    private boolean manger = false;
    private String managerToken = "";

}
