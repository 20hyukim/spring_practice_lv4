package com.sparta.lv3project.service;

import com.sparta.lv3project.dto.User.SignupRequestDto;
import com.sparta.lv3project.entity.User.User;
import com.sparta.lv3project.entity.User.UserDepartmentEnum;
import com.sparta.lv3project.entity.User.UserRoleEnum;
import com.sparta.lv3project.jwt.JwtUtil;
import com.sparta.lv3project.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String email = requestDto.getEmail();
        String password = passwordEncoder.encode(requestDto.getPassword());
        //회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if(checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        //email 중복 확인
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if(checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        UserRoleEnum role = UserRoleEnum.STAFF;
        //role 확인
        if (Objects.equals(requestDto.getDepartment(), UserDepartmentEnum.CURRICULUM) || Objects.equals(requestDto.getDepartment(), UserDepartmentEnum.DEVELOPMENT)) {
            role = UserRoleEnum.MANAGER;
        }


        User user = new User(username, email, password, role, requestDto.getDepartment());
        userRepository.save(user);


    }





}



/*    public String login(LoginRequestDto requestDto) throws Exception {
        String email = requestDto.getEmail();
        User user = userRepository.findByEmail(email).get();
        String token = jwtUtil.createToken(user.getUsername(), user.getRole());
        return token;
    }*/