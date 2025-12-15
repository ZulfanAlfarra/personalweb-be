package com.zulfan.personal_web.mapper;

import com.zulfan.personal_web.dto.UserRequestDto;
import com.zulfan.personal_web.dto.UserResponseDto;
import com.zulfan.personal_web.dto.WalletResponseDto;
import com.zulfan.personal_web.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDto toDtoResponse(User user){
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getWallets()
                        .stream()
                        .map(w -> new WalletResponseDto(
                                w.getId(),
                                w.getName(),
                                w.getBalance(),
                                w.getCreatedAt(),
                                w.getUpdatedAt()
                        ))
                        .toList()
        );
    }

    public User toEntity(UserRequestDto dto){
        return User.builder()
                .username(dto.username())
                .email(dto.email())
                .password(dto.username())
                .build();
    }
}
