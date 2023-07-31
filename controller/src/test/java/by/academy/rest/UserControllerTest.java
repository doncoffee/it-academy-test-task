package by.academy.rest;

import by.academy.dto.UserCreateDto;
import by.academy.dto.UserReadDto;
import by.academy.service.UserService;
import by.academy.util.MockUtil;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void create() throws Exception {
        UserCreateDto userCreateDto = MockUtil.getUserCreateDto();
        UserReadDto userReadDto = MockUtil.getUserReadDto();
        when(userService.createUser(userCreateDto)).thenReturn(userReadDto);

        // Perform the POST request
        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstname\": \"John\", \"lastname\": \"Harris\", \"middleName\": \"Oliver\", " +
                                "\"email\": \"johnharris@gmail.com\", \"role\": \"ADMINISTRATOR\" }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.fullName").value("Harris John Oliver"))
                .andExpect(jsonPath("$.email").value("johnharris@gmail.com"))
                .andExpect(jsonPath("$.role").value("ADMINISTRATOR"));


        // Verify that the service method was called
        verify(userService, times(1)).createUser(userCreateDto);
        verifyNoMoreInteractions(userService);
    }

    @Test
    void getAllUsers() throws Exception {
        UserReadDto user = MockUtil.getUserReadDto();
        Page<UserReadDto> users = new PageImpl<>(Collections.singletonList(user));
        when(userService.findAllUsers(any(Pageable.class))).thenReturn(users);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].fullName").value("Harris John Oliver"))
                .andExpect(jsonPath("$.content[0].email").value("johnharris@gmail.com"))
                .andExpect(jsonPath("$.content[0].role").value("ADMINISTRATOR"));

        // Verify that the service method was called
        verify(userService, times(1)).findAllUsers(any(Pageable.class));
        verifyNoMoreInteractions(userService);

    }

    @Test
    void findById() throws Exception {
        UserReadDto userReadDto = MockUtil.getUserReadDto();
        when(userService.findUserById(1L)).thenReturn(Optional.of(userReadDto));

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.fullName").value("Harris John Oliver"))
                .andExpect(jsonPath("$.email").value("johnharris@gmail.com"))
                .andExpect(jsonPath("$.role").value("ADMINISTRATOR"));

        // Verify that the service method was called
        verify(userService, times(1)).findUserById(1L);
        verifyNoMoreInteractions(userService);
    }

    @Test
    void update() throws Exception {
        UserCreateDto userCreateDto = MockUtil.getUserCreateDto();

        // Mock the service response
        UserReadDto userReadDto = MockUtil.getUserReadDto();
        when(userService.updateUser(1L, userCreateDto)).thenReturn(Optional.of(userReadDto));

        // Perform the PUT request
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstname\": \"John\", \"lastname\": \"Harris\", \"middleName\": \"Oliver\", " +
                                "\"email\": \"johnharris@gmail.com\", \"role\": \"ADMINISTRATOR\" }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.fullName").value("Harris John Oliver"))
                .andExpect(jsonPath("$.email").value("johnharris@gmail.com"))
                .andExpect(jsonPath("$.role").value("ADMINISTRATOR"));

        // Verify that the service method was called
        verify(userService, times(1)).updateUser(1L, userCreateDto);
        verifyNoMoreInteractions(userService);
    }

    @Test
    void delete() throws Exception {
        // Mock the service response
        when(userService.deleteUser(1L)).thenReturn(true);

        // Perform the DELETE request
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/1"))
                .andExpect(status().isNoContent());

        // Verify that the service method was called
        verify(userService, times(1)).deleteUser(1L);
        verifyNoMoreInteractions(userService);
    }
}