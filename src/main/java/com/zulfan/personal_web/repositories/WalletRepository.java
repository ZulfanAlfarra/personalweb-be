package com.zulfan.personal_web.repositories;

import com.zulfan.personal_web.dto.TransactionResponseDto;
import com.zulfan.personal_web.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    boolean existsByUserIdAndName(Long userId, String name);

}
