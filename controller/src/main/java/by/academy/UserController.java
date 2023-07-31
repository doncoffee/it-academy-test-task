package by.academy;

import by.academy.dto.UserCreateDto;
import by.academy.dto.UserReadDto;
import by.academy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LogManager.getLogger(UserController.class);


    @PostMapping
    public UserReadDto create(@RequestBody UserCreateDto userCreateDto) {
        logger.info("Creating a new user: {}", userCreateDto);
        return userService.createUser(userCreateDto);
    }

    @GetMapping
    public ResponseEntity<Page<UserReadDto>> getAllUsers(@PageableDefault(size = 10) Pageable pageable) {
        logger.info("Fetching all users");
        Page<UserReadDto> users = userService.findAllUsers(pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public UserReadDto findById(@PathVariable Long id) {
        logger.info("Fetching user by ID: {}", id);
        return userService.findUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public UserReadDto update(@PathVariable Long id,
                              @RequestBody UserCreateDto userCreateDto) {
        logger.info("Updating user with ID: {}", id);
        return userService.updateUser(id, userCreateDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        logger.info("Deleting user with ID: {}", id);
        return userService.deleteUser(id)
                ? noContent().build()
                : notFound().build();
    }
}
