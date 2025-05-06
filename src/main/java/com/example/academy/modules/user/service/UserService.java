package com.example.academy.modules.user.service;

import com.example.academy.core.domain.mapper.user.UserMapper;
import com.example.academy.core.excaption.ResourceNotFoundException;
import com.example.academy.modules.user.entity.UserEntity;
import com.example.academy.modules.user.entity.dto.UserDto;
import com.example.academy.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public UserDto getUserById(Long id) {
        UserEntity userEntity = userRepository.getUserEntityById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + id + " not found")
        );
        return UserMapper.toDtoWith(userEntity);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto).collect(Collectors.toList());
    }


    /*public UserDto getUserByUsername(String username) {
        return UserMapper.toDtoWith(
                userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("user not found with this "+username)
                )
        );
    }*/

    /*public Object getUserByUsername(String usernameOrEmail) {


    }*/
}
