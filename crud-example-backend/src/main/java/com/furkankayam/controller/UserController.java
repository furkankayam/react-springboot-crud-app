package com.furkankayam.controller;

import com.furkankayam.dto.UserRequestResponseDto;
import com.furkankayam.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    //Constructor
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //CREATE
    @PostMapping
    public ResponseEntity<UserRequestResponseDto> createUser(@RequestBody UserRequestResponseDto userRequest) {
        return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.CREATED);
    }

    //DELETE
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable String username) {
        return new ResponseEntity<>(userService.deleteUser(username), HttpStatus.OK);
    }

    //ALL
    @GetMapping
    public ResponseEntity<List<UserRequestResponseDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    //UPDATE
    @PostMapping("/update/{username}")
    public ResponseEntity<UserRequestResponseDto> updateUser(@PathVariable String username,
                                                             @RequestBody UserRequestResponseDto userRequest) {
        return new ResponseEntity<>(userService.updateUser(username, userRequest), HttpStatus.OK);
    }

    //SEARCH
    @GetMapping("/search")
    public ResponseEntity<List<UserRequestResponseDto>> searchUsers(@RequestParam String text) {
        return new ResponseEntity<>(userService.searchUsers(text), HttpStatus.OK);
    }

}
