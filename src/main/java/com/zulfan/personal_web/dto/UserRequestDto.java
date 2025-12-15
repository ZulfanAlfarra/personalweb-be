package com.zulfan.personal_web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @NotBlank(message = "Username is required")
        @Size(min = 2, message = "Username at least 2 characters")
        String username,

        @Email(message = "Invalid email format")
        @NotBlank(message = "email is required")
        String email,

        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Minimum eight characters, at least one uppercase letter, one lowercase letter and one number")
        String password
) {
}
