package com.sparta.lv3project.dto;

import com.sparta.lv3project.entity.User.UserDepartmentEnum;
import com.sparta.lv3project.entity.User.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private String username;

    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 8, max = 15)
    private String password;

    private UserDepartmentEnum department;

    private String managerToken = "";

}
