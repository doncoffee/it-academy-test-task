package by.academy.mapper.impl;

import by.academy.dto.UserReadDto;
import by.academy.entity.User;
import by.academy.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {

    @Override
    public User mapToEntity(UserReadDto object) {
        throw new UnsupportedOperationException("Unsupported operation in current mapper class");
    }

    @Override
    public UserReadDto mapToDto(User object) {
        return UserReadDto.builder()
                .id(object.getId())
                .fullName(createFullName(object))
                .email(object.getEmail())
                .role(object.getRole())
                .build();
    }

    private String createFullName(User user) {
        return user.getLastname() + " " + user.getFirstname() + " "
                + user.getMiddleName();
    }
}
