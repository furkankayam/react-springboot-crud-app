package com.furkankayam;

import com.furkankayam.dto.UserRequestResponseDto;
import com.furkankayam.model.User;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    public static User generateUser() {
        User user = new User();
        user.setFirstName("Test-Name");
        user.setLastName("Test-LastName");
        user.setUsername("Test-Username");
        user.setEmail("Test-Email");
        return user;
    }

    public static User generateUser1() {
        User user = new User();
        user.setFirstName("Test-Name1");
        user.setLastName("Test-LastName1");
        user.setUsername("Test-Username1");
        user.setEmail("Test-Email1");
        return user;
    }

    public static UserRequestResponseDto generateUserRequestResponseDto() {
        return new UserRequestResponseDto(
                "Test-Name",
                "Test-LastName",
                "Test-Username",
                "Test-Email"
        );
    }

    public static UserRequestResponseDto generateUserRequestResponseDtoUpdate() {
        return new UserRequestResponseDto(
                "Test-Name1",
                "Test-LastName1",
                "Test-Username1",
                "Test-Email1"
        );
    }

    public static List<User> generateUserList() {
        List<User> userList = new ArrayList<>();

        userList.add(new User(1L, "Alice", "Smith", "al", "alice@example.com"));
        userList.add(new User(2L, "Bob", "Johnson", "bob", "bob@example.com"));
        userList.add(new User(3L, "Charlie", "Brown", "charlie", "charlie@example.com"));

        return userList;
    }

}
