package com.zulfan.personal_web.controllers;

import com.zulfan.personal_web.dto.UserDto;
import com.zulfan.personal_web.dto.UserResponse;
import com.zulfan.personal_web.entities.User;
import com.zulfan.personal_web.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createNewUser(@RequestBody UserDto userDto){
        UserResponse createdUserDto = userService.createNewUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map> getUserById(@PathVariable Long id){
        UserResponse userRes = userService.getUserById(id);
        return ResponseEntity.ok(
                Map.of(
                        "message", "success get user with id " + id,
                        "data", userRes
                )
        );
    }
}
