package com.zulfan.personal_web.controllers;

import com.zulfan.personal_web.dto.ApiResponse;
import com.zulfan.personal_web.dto.UserRequestDto;
import com.zulfan.personal_web.dto.UserResponseDto;
import com.zulfan.personal_web.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createNewUser(@Valid @RequestBody UserRequestDto userDto){
        UserResponseDto createdUserDto = userService.createNewUser(userDto);
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.CREATED.value(), "Succeed create new user", createdUserDto)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        UserResponseDto userRes = userService.getUserById(id);
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK.value(), "Found user with id " + id, userRes)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userDto){
        UserResponseDto userUpdated = userService.updateUser(id, userDto);
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK.value(), "Succeed update user with id " + id, userUpdated)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
