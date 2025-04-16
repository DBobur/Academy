package com.example.academy;

import com.example.academy.modules.user.entity.UserEntity;
import com.example.academy.modules.user.enums.UserRole;
import com.example.academy.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initSuperAdmin(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("super_admin").isEmpty()) {
                UserEntity superAdmin = UserEntity.builder()
                        .fullName("Super Admin")
                        .username("super_admin")
                        .password(passwordEncoder.encode("super_1234"))
                        .email("super_admin_1234@example.com")
                        .number("+998901234567")
                        .address("Tashkent, Uzbekistan")
                        .dateOfBirth(LocalDate.of(2003, 3, 1))
                        .role(UserRole.SUPER)
                        .isDeleted(false)
                        .build();

                userRepository.save(superAdmin);
                System.out.println("✅ SUPER admin user created.");
            } else {
                System.out.println("ℹ️ SUPER admin user already exists.");
            }
        };
    }
}

