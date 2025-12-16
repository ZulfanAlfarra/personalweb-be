package com.zulfan.personal_web.mapper;

import com.zulfan.personal_web.dto.TransactionRequestDto;
import com.zulfan.personal_web.dto.TransactionResponseDto;
import com.zulfan.personal_web.entities.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionResponseDto toDtoResponse(Transaction transaction){
        return new TransactionResponseDto(
                transaction.getId(),
                transaction.getWallet().getId(),
                transaction.getType(),
                transaction.getAmount(),
                transaction.getCategory(),
                transaction.getDescription()
        );
    }

    public Transaction toEntity(TransactionRequestDto dto){
        return Transaction.builder()
                .type(dto.type())
                .amount(dto.amount())
                .category(dto.category())
                .description(dto.description())
                .build();
    }
}
