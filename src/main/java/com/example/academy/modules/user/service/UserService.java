package com.example.academy.modules.user.service;

import com.example.academy.core.domain.mapper.UserMapper;
import com.example.academy.core.domain.request.user.UserUpdateRequest;
import com.example.academy.core.domain.response.user.UserResponse;
import com.example.academy.core.domain.specific.UserSpecifications;
import com.example.academy.core.excaption.ResourceNotFoundException;
import com.example.academy.core.heper.BaseHelper;
import com.example.academy.modules.user.entity.UserEntity;
import com.example.academy.modules.user.enums.UserRole;
import com.example.academy.modules.user.repository.UserRepository;
import com.example.academy.modules.user.util.PasswordResetToken;
import com.example.academy.modules.user.util.PasswordResetTokenRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Transactional
    public void updateUser(Long id, UserUpdateRequest userUpdateRequest) {

        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserEntity not found with id: " + id));

        BaseHelper.updateIfPresent(userUpdateRequest.getFullName(), existingUser::setFullName);
        BaseHelper.updateIfPresent(userUpdateRequest.getUsername(), existingUser::setUsername);
        BaseHelper.updateIfPresent(
                passwordEncoder.encode(userUpdateRequest.getPassword()),
                existingUser::setPassword
        );
        BaseHelper.updateIfPresent(userUpdateRequest.getEmail(), existingUser::setEmail);
        BaseHelper.updateIfPresent(userUpdateRequest.getNumber(), existingUser::setNumber);

        userRepository.save(existingUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        UserEntity user = userRepository.findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new ResourceNotFoundException("UserEntity with id " + id + " not found or already deleted"));

        user.setDeleted(true);
        userRepository.save(user);
    }

    @Transactional
    public void restoreUser(Long id) {
        UserEntity user = userRepository.findByIdAndIsDeleted(id, true)
                .orElseThrow(() -> new ResourceNotFoundException("UserEntity with id " + id + " not found or is not deleted"));

        user.setDeleted(false);
        userRepository.save(user);
    }



    public Page<UserResponse> getAllUsers(String roleName, Pageable pageable, boolean active) {
        Specification<UserEntity> spec = Specification.where(UserSpecifications.isActive(active));

        if (roleName != null && !roleName.isBlank()) {
            spec = spec.and(UserSpecifications.hasRoleName(UserRole.valueOf(roleName)));
        }

        Page<UserEntity> users = userRepository.findAll(spec, pageable);
        return users.map(UserMapper::entityToResponse);
    }




    public UserResponse getUserById(Long id) {
        return UserMapper.entityToResponse(userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("UserEntity with id " + id + " not found")));
    }

    public void resetPasswordByIdentifier(String identifier) {
        UserEntity user = userRepository.findByEmailOrNumber(identifier, identifier)
                .orElseThrow(() -> new ResourceNotFoundException("UserEntity not found with given "+identifier));

        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken(user.getId(), token, LocalDateTime.now().plusHours(1));

        passwordResetTokenRepository.save(resetToken);

        if (identifier.contains("@")) {
            emailService.sendEmail(identifier,"davlatboyev2024@gmail.com",token);
        }
    }

    public void updatePasswordWithToken(String token, String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid or expired token"));

        UserEntity user = userRepository.findById(resetToken.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("UserEntity with id " + resetToken.getUserId() + " not found"));

        if (resetToken.getExpiration().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token has expired");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        passwordResetTokenRepository.delete(resetToken);
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + username)
        );
    }
}

