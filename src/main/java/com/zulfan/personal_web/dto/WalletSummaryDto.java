package com.zulfan.personal_web.dto;

import java.math.BigDecimal;

public record WalletSummaryDto(
        Long id,
        String name,
        BigDecimal balance
) {
}
