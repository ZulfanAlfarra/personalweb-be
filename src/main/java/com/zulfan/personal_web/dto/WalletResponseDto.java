package com.zulfan.personal_web.dto;

import com.zulfan.personal_web.entities.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record WalletResponseDto(
        Long id,
        String name,
        BigDecimal balance,
        List<TransactionResponseDto> transactions,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
