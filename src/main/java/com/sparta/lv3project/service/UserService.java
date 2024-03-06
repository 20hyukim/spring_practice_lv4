package com.sparta.lv3project.service;

import com.sparta.lv3project.dto.SignupRequestDto;
import com.sparta.lv3project.entity.User.User;
import com.sparta.lv3project.entity.User.UserRoleEnum;
import com.sparta.lv3project.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = passwordEncoder.encode(requestDto.getPassword());

        //email 중복 확인
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if(checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        //role 확인
        UserRoleEnum role = UserRoleEnum.STAFF;
        if(requestDto.isManger()){
            if (!ADMIN_TOKEN.equals(requestDto.getManagerToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.STAFF;
        }

        User user = new User(email, password, role, requestDto.getDepartment());
        userRepository.save(user);


    }
}
