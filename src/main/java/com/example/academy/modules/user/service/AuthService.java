package com.example.academy.modules.user.service;

import com.example.academy.core.config.security.JwtTokenUtil;
import com.example.academy.modules.user.entity.mapper.UserMapper;
import com.example.academy.core.domain.response.user.UserSuccessResponse;
import com.example.academy.core.excaption.PasswordNotMatchesException;
import com.example.academy.modules.user.entity.UserEntity;
import com.example.academy.modules.user.entity.dto.LoginDto;
import com.example.academy.modules.user.entity.dto.UserDto;
import com.example.academy.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public UserSuccessResponse login(LoginDto loginDto) {
        UserEntity userEntity = userRepository.findByUsername(loginDto.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("Username " + loginDto.getUsername() + " not found")
        );
        if (passwordEncoder.matches(loginDto.getPassword(), userEntity.getPassword())) {
            return UserSuccessResponse.builder()
                    .userDto(UserMapper.toDtoWith(userEntity))
                    .jwtToken(jwtTokenUtil.generateToken(userEntity.getUsername()))
                    .build();
        } else {
            throw new PasswordNotMatchesException("Password not matches?");
        }

    }

    @Transactional
    public UserDto register(UserDto userDto) {
        UserEntity userEntity = userDto.getContract() != null ?
                UserMapper.toEntityWith(userDto):
                UserMapper.toEntity(userDto);
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return UserMapper.toDtoWith(userRepository.save(userEntity));
    }
}
