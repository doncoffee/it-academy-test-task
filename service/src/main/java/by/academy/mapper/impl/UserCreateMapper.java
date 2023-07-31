package by.academy.mapper.impl;

import by.academy.dto.UserCreateDto;
import by.academy.entity.User;
import by.academy.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserCreateMapper implements Mapper<User, UserCreateDto> {
    @Override
    public User mapToEntity(UserCreateDto object) {
        return User.builder()
                .id(object.getId())
                .lastname(object.getLastname())
                .firstname(object.getFirstname())
                .middleName(object.getMiddleName())
                .email(object.getEmail())
                .role(object.getRole())
                .build();
    }

    @Override
    public UserCreateDto mapToDto(User object) {
        return UserCreateDto.builder()
                .id(object.getId())
                .lastname(object.getLastname())
                .firstname(object.getFirstname())
                .middleName(object.getMiddleName())
                .email(object.getEmail())
                .role(object.getRole())
                .build();
    }
}
