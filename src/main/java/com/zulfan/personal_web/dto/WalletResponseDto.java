package com.zulfan.personal_web.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record WalletResponseDto(
        Long id,
        String name,
        BigDecimal balance,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
