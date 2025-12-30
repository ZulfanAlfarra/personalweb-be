package com.zulfan.personal_web.dto;

import java.math.BigDecimal;
import java.util.List;

public record WalletSummaryResponseDto(
        BigDecimal totalBalance,
        BigDecimal totalIncome,
        BigDecimal totalExpense,
        List<TransactionResponseDto> transactions
) {
}
