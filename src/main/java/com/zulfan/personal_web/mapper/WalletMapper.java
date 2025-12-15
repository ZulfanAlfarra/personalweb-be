package com.zulfan.personal_web.mapper;

import com.zulfan.personal_web.dto.WalletRequestDto;
import com.zulfan.personal_web.dto.WalletResponseDto;
import com.zulfan.personal_web.entities.Wallet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class WalletMapper {

    public WalletResponseDto toDtoResponse(Wallet wallet){
        return new WalletResponseDto(
                wallet.getId(),
                wallet.getName(),
                wallet.getBalance(),
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
