package by.academy.service;

import by.academy.dto.UserCreateDto;
import by.academy.dto.UserReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    UserReadDto createUser(UserCreateDto userCreateDto);
    Page<UserReadDto> findAllUsers(Pageable pageable);
    Optional<UserReadDto> updateUser(Long id, UserCreateDto userCreateDto);
    Optional<UserReadDto> findUserById(Long id);
    boolean deleteUser(Long id);
}
