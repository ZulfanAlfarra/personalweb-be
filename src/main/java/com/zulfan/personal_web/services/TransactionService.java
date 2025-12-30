package com.zulfan.personal_web.services;

import com.zulfan.personal_web.dto.TransactionRequestDto;
import com.zulfan.personal_web.dto.TransactionResponseDto;
import com.zulfan.personal_web.entities.Transaction;
import com.zulfan.personal_web.entities.TransactionType;
import com.zulfan.personal_web.entities.Wallet;
import com.zulfan.personal_web.exceptions.BadRequestException;
import com.zulfan.personal_web.exceptions.ResourceNotFoundException;
import com.zulfan.personal_web.mapper.TransactionMapper;
import com.zulfan.personal_web.repositories.TransactionRepository;
import com.zulfan.personal_web.repositories.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {
    private final WalletRepository walletRepository;
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;

//    public TransactionResponseDto create(Long wallet_id, TransactionRequestDto dto){
//        Wallet wallet = walletRepository.findById(wallet_id).orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id " + wallet_id));
//        Transaction transaction = transactionMapper.toEntity(dto);
//        transaction.setWallet(wallet);
//        Transaction saved = transactionRepository.save(transaction);
//
//        return transactionMapper.toDtoResponse(saved);
//    }

    @Transactional
    public TransactionResponseDto createTransaction(Long wallet_id, TransactionRequestDto dto){
        if(dto.amount() == null || dto.amount().compareTo(BigDecimal.ZERO) <= 0){
            throw new BadRequestException("Amount must be greater than zero");
        }

        Wallet wallet = walletRepository.findById(wallet_id).orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id" + wallet_id));
        Transaction transaction = transactionMapper.toEntity(dto);
        transaction.setWallet(wallet);

        switch (dto.type()){
            case INCOME ->  {
                wallet.setBalance(wallet.getBalance().add(dto.amount()));
            }
            case EXPENSE -> {
                if(wallet.getBalance().compareTo(dto.amount())< 0){
                    throw new BadRequestException("Insufficient balance");
                }
                wallet.setBalance(wallet.getBalance().subtract(dto.amount()));
            }
        }

        Transaction saved = transactionRepository.save(transaction);

        return transactionMapper.toDtoResponse(saved);
    }

    public List<TransactionResponseDto> getTransactions(Long wallet_id){
        Wallet wallet = walletRepository.findById(wallet_id).orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id " + wallet_id));
        return wallet.getTransactions()
                .stream()
                .map(transactionMapper::toDtoResponse)
                .toList();
    }

    @Transactional
    public TransactionResponseDto updateTransaction(Long wallet_id, Long transaction_id, TransactionRequestDto dto){
        Wallet wallet = walletRepository.findById(wallet_id).orElseThrow(()-> new ResourceNotFoundException("Wallet not found with id " + wallet_id));
        Transaction existing = transactionRepository.findById(transaction_id).orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id " + transaction_id));

        if (!existing.getWallet().getId().equals(wallet_id)){
            throw new BadRequestException("Transaction does not belong to wallet");
        }

        if(existing.getType() == TransactionType.INCOME){
            wallet.setBalance(wallet.getBalance().subtract(existing.getAmount()));
        } else if (existing.getType() == TransactionType.EXPENSE) {
            wallet.setBalance(wallet.getBalance().add(existing.getAmount()));
        }

        if(dto.amount() == null || dto.amount().compareTo(BigDecimal.ZERO) <= 0){
            throw new BadRequestException("Amount must be greater than zero");
        }

        switch (dto.type()){
            case INCOME -> wallet.setBalance(wallet.getBalance().add(dto.amount()));
            case EXPENSE -> {
                if(wallet.getBalance().compareTo(dto.amount()) < 0){
                    throw new BadRequestException("Insufficient balance");
                }
                wallet.setBalance(wallet.getBalance().subtract(dto.amount()));
            }
        }

        existing.setType(dto.type());
        existing.setAmount(dto.amount());
        existing.setCategory(dto.category());
        existing.setDescription(dto.description());

        return transactionMapper.toDtoResponse(existing);
    }
}
