package com.zulfan.personal_web.repositories;

import com.zulfan.personal_web.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
