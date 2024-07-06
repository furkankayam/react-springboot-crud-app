package com.furkankayam.dto.converter;

import com.furkankayam.dto.UserRequestResponseDto;
import com.furkankayam.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    public User toUserRequestResponseDto (UserRequestResponseDto userRequest) {
        User user = new User();
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setEmail(userRequest.email());
        user.setUsername(userRequest.username());
        return user;
    }

    public UserRequestResponseDto toUserRequestResponseDto (User user) {
        return new UserRequestResponseDto(
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail()
        );
    }

    public List<UserRequestResponseDto> toUserRequestResponseDtoList (List<User> users) {
        List<UserRequestResponseDto> userRequestResponseDtos = new ArrayList<>();
        return users.stream()
                .map(this::toUserRequestResponseDto)
                .toList();
    }

}
