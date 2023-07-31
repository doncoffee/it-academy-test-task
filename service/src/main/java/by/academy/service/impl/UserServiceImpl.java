package by.academy.service.impl;

import by.academy.dto.UserCreateDto;
import by.academy.dto.UserReadDto;
import by.academy.entity.User;
import by.academy.mapper.impl.UserCreateMapper;
import by.academy.mapper.impl.UserReadMapper;
import by.academy.repo.UserRepository;
import by.academy.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserCreateMapper userCreateMapper;
    private final UserReadMapper userReadMapper;

    @Override
    public UserReadDto createUser(UserCreateDto userCreateDto) {
        return Optional.of(userCreateDto)
                .map(userCreateMapper::mapToEntity)
                .map(userRepository::save)
                .map(userReadMapper::mapToDto)
                .orElseThrow();
    }

    @Override
    public Page<UserReadDto> findAllUsers(Pageable pageable) {
        return userRepository.findAllByOrderByEmailAsc(pageable)
                .map(userReadMapper::mapToDto);
    }

    @Override
    public Optional<UserReadDto> updateUser(Long id, UserCreateDto userCreateDto) {
        return userRepository.findById(id)
                .map(entity -> copy(userCreateDto, entity))
                .map(userRepository::save)
                .map(userReadMapper::mapToDto);
    }

    private User copy(UserCreateDto userCreateDto, User user) {
        user.setId(userCreateDto.getId());
        user.setLastname(userCreateDto.getLastname());
        user.setFirstname(userCreateDto.getFirstname());
        user.setMiddleName(userCreateDto.getMiddleName());
        user.setEmail(userCreateDto.getEmail());
        user.setRole(userCreateDto.getRole());
        return user;
    }

    @Override
    public Optional<UserReadDto> findUserById(Long id) {
        return userRepository.findById(id)
                .map(userReadMapper::mapToDto);
    }

    @Override
    public boolean deleteUser(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    return true;
                })
                .orElse(false);
    }
}
