package com.zulfan.personal_web.dto;

import com.zulfan.personal_web.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record WalletRequestDto(
        @NotBlank(message = "Wallet name is required")
        @Size(min = 2, max = 50, message = "Wallet name must be between 2 and 50 characters")
        String name)
{}
