package com.zulfan.personal_web.repositories;

import com.zulfan.personal_web.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
