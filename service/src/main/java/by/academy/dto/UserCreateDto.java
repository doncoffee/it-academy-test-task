package by.academy.dto;

import by.academy.entity.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserCreateDto {
    Long id;
    String lastname;
    String firstname;
    String middleName;
    String email;
    Role role;
}
