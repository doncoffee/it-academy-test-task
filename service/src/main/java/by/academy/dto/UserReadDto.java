package by.academy.dto;

import by.academy.entity.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserReadDto {
    Long id;
    String fullName;
    String email;
    Role role;
}
