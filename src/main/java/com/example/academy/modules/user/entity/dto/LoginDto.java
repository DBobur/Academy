package com.example.academy.modules.user.entity.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginDto{
    private String username;
    private String password;
}
