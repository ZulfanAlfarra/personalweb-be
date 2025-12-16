package com.zulfan.personal_web.mapper;

import com.zulfan.personal_web.dto.WalletRequestDto;
import com.zulfan.personal_web.dto.WalletResponseDto;
import com.zulfan.personal_web.entities.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WalletMapper {

    private final TransactionMapper transactionMapper;

    public WalletResponseDto toDtoResponse(Wallet wallet){
        return new WalletResponseDto(
                wallet.getId(),
                wallet.getName(),
                wallet.getBalance(),
                wallet.getTransactions() == null
                        ? List.of()
                        : wallet.getTransactions().stream().map(transactionMapper::toDtoResponse).toList(),
                wallet.getCreatedAt(),
                wallet.getUpdatedAt()
        );
    }

    public Wallet toEntity(WalletRequestDto dto){
        return Wallet.builder()
                .name(dto.name())
                .build();
    }
}
