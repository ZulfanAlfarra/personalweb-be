package com.zulfan.personal_web.dto;

import com.zulfan.personal_web.entities.TransactionType;

import java.math.BigDecimal;

public record TransactionResponseDto(
        Long id,
        Long wallet_id,
        TransactionType type,
        BigDecimal amount,
        String category,
        String description
) {
}
