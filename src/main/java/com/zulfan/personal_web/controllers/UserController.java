package com.zulfan.personal_web.controllers;

import com.zulfan.personal_web.dto.UserDto;
import com.zulfan.personal_web.dto.UserResponse;
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
    public ResponseEntity<?> createNewUser(@Valid @RequestBody UserDto userDto){
        UserResponse createdUserDto = userService.createNewUser(userDto);
        return ResponseEntity.ok(createdUserDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map> getUserById(@PathVariable Long id){
        UserResponse userRes = userService.getUserById(id);
        return ResponseEntity.ok(
                Map.of(
                        "status", 200,
                        "message", "success get user with id " + id,
                        "data", userRes
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        UserResponse userUpdated = userService.updateUser(id, userDto);
        return ResponseEntity.ok(userUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
