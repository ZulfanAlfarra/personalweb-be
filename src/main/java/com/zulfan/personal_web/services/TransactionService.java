package com.zulfan.personal_web.services;

import com.zulfan.personal_web.dto.TransactionRequestDto;
import com.zulfan.personal_web.dto.TransactionResponseDto;
import com.zulfan.personal_web.entities.Transaction;
import com.zulfan.personal_web.entities.Wallet;
import com.zulfan.personal_web.exceptions.ResourceNotFoundException;
import com.zulfan.personal_web.mapper.TransactionMapper;
import com.zulfan.personal_web.repositories.TransactionRepository;
import com.zulfan.personal_web.repositories.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {
    private final WalletRepository walletRepository;
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;

    public TransactionResponseDto create(Long wallet_id, TransactionRequestDto dto){
        Wallet wallet = walletRepository.findById(wallet_id).orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id " + wallet_id));
        Transaction transaction = transactionMapper.toEntity(dto);
        transaction.setWallet(wallet);
        Transaction saved = transactionRepository.save(transaction);

        return transactionMapper.toDtoResponse(saved);
    }
}
