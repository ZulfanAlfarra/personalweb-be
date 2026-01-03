package com.zulfan.personal_web.mapper;

import com.zulfan.personal_web.dto.UserRequestDto;
import com.zulfan.personal_web.dto.UserResponseDto;
import com.zulfan.personal_web.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final WalletMapper walletMapper;

    public UserResponseDto toDtoResponse(UserEntity user){
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getWallets() == null
                        ? List.of()
                        : user.getWallets().stream().map(walletMapper::toDtoSummary).toList()
        );
    }

    public UserEntity toEntity(UserRequestDto dto){
        return UserEntity.builder()
                .username(dto.username())
                .email(dto.email())
                .password(dto.password())
                .build();
    }
}
