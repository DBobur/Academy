package com.example.academy.modules.user.service;

import com.example.academy.core.config.CustomUserdetailsService;
import com.example.academy.core.config.JwtTokenUtil;
import com.example.academy.core.domain.mapper.UserMapper;
import com.example.academy.core.domain.request.user.LoginRequest;
import com.example.academy.core.domain.request.user.UserRequest;
import com.example.academy.core.domain.response.user.UserResponse;
import com.example.academy.modules.user.entity.UserEntity;
import com.example.academy.modules.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserdetailsService service;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Transactional
    public UserResponse save(UserRequest request) {
        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .fullName(request.getFullName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .number(request.getNumber())
                .address(request.getAddress())
                .dateOfBirth(LocalDate.parse(request.getDateOfBirth()))

                .build();
        userRepository.save(user);
        return UserMapper.entityToResponse(user);
    }

    public String login(@Valid LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        UserEntity byUsername = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("username " + username + " not found")
        );

        if (!passwordEncoder.matches(password, byUsername.getPassword())) {
            throw new BadCredentialsException("Incorrect username or password");
        }

        return jwtTokenUtil.generateToken(username);
    }
}
