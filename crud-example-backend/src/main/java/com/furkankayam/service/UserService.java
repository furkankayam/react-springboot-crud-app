package com.furkankayam.service;

import com.furkankayam.dto.UserRequestResponseDto;
import com.furkankayam.dto.converter.UserConverter;
import com.furkankayam.exception.UserNotFoundExcaption;
import com.furkankayam.model.User;
import com.furkankayam.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    //Construtor
    public UserService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    //FINDBYID
    public User isThereUser(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new UserNotFoundExcaption("User with username " + username + " not found")
                );
    }

    //CREATE
    public UserRequestResponseDto createUser(UserRequestResponseDto userRequest) {
        User user = userConverter.toUserRequestResponseDto(userRequest);
        userRepository.save(user);
        return userRequest;
    }

    //DELETE
    public Map<String, Boolean> deleteUser(String username) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("Delete: ", true);
        userRepository.deleteById(isThereUser(username).getId());
        return response;
    }

    //ALL
    public List<UserRequestResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userConverter.toUserRequestResponseDtoList(users);
    }

    //UPDATE
    public UserRequestResponseDto updateUser(String username,
                                             UserRequestResponseDto userRequest) {
        User user = isThereUser(username);
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setEmail(userRequest.email());
        user.setUsername(userRequest.username());
        userRepository.save(user);
        return userRequest;

    }

    //SEARCH
    public List<UserRequestResponseDto> searchUsers(String text) {
        List<User> users = userRepository.searchUsers(text);
        return userConverter.toUserRequestResponseDtoList(users);
    }

}
