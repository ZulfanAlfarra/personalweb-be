package com.zulfan.personal_web.repositories;

import com.zulfan.personal_web.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t where t.wallet.id = :wallet_id AND t.createdAt BETWEEN :startDate  AND :endDate")
    List<Transaction> findByWalletAndDateRange(Long wallet_id, LocalDateTime startDate, LocalDateTime endDate);
}
