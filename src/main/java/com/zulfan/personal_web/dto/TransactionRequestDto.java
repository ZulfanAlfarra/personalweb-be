package com.zulfan.personal_web.dto;

import com.zulfan.personal_web.entities.TransactionType;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

public record TransactionRequestDto(
        TransactionType type,
        BigDecimal amount,
        String category,
        String description
) {
}
