package com.zulfan.personal_web.dto;

import com.zulfan.personal_web.entities.Wallet;

import java.util.List;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        List<WalletSummaryDto> wallets
) {}
