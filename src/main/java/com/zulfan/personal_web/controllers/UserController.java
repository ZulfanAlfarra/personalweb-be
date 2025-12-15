package com.zulfan.personal_web.controllers;

import com.zulfan.personal_web.dto.UserRequestDto;
import com.zulfan.personal_web.dto.UserResponseDto;
import com.zulfan.personal_web.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> createNewUser(@Valid @RequestBody UserRequestDto userDto){
        UserResponseDto createdUserDto = userService.createNewUser(userDto);
        return ResponseEntity.ok(createdUserDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map> getUserById(@PathVariable Long id){
        UserResponseDto userRes = userService.getUserById(id);
        return ResponseEntity.ok(
                Map.of(
                        "status", 200,
                        "message", "success get user with id " + id,
                        "data", userRes
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userDto){
        UserResponseDto userUpdated = userService.updateUser(id, userDto);
        return ResponseEntity.ok(userUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
