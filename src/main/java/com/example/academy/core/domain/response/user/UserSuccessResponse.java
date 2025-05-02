package com.example.academy.core.domain.response.user;

import com.example.academy.modules.user.entity.dto.UserDto;
import lombok.Builder;

@Builder
public record UserSuccessResponse(
     UserDto userDto,
     String jwtToken
){}

