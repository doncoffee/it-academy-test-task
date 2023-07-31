package by.academy.util;

import by.academy.dto.UserCreateDto;
import by.academy.dto.UserReadDto;
import by.academy.entity.Role;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MockUtil {

    public static UserCreateDto getUserCreateDto() {
        return UserCreateDto.builder()
                .lastname("Harris")
                .firstname("John")
                .middleName("Oliver")
                .email("johnharris@gmail.com")
                .role(Role.ADMINISTRATOR)
                .build();
    }

    public static UserReadDto getUserReadDto() {
        return UserReadDto.builder()
                .fullName("Harris John Oliver")
                .email("johnharris@gmail.com")
                .role(Role.ADMINISTRATOR)
                .build();
    }
}
