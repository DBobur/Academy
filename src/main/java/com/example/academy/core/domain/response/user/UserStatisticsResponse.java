package com.example.academy.core.domain.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserStatisticsResponse {
    private long totalUsers;
    private long activeContracts;
    private long expiredContracts;
    private Map<String, Long> roleCounts; // Masalan: { USER: 40, ADMIN: 3 }
}

