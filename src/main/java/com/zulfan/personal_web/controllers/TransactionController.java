package com.zulfan.personal_web.controllers;

import com.zulfan.personal_web.dto.ApiResponse;
import com.zulfan.personal_web.dto.TransactionRequestDto;
import com.zulfan.personal_web.dto.TransactionResponseDto;
import com.zulfan.personal_web.services.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("{wallet_id}/transactions")
    public ResponseEntity<?> createTransaction(@PathVariable Long wallet_id, @Valid @RequestBody TransactionRequestDto dto){
        TransactionResponseDto transaction = transactionService.createTransaction(wallet_id, dto);
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.CREATED.value(), "Succeed create transaction", transaction)
        );
    }

    @GetMapping("{wallet_id}/transactions")
    public ResponseEntity<?> getWalletTransaction(@PathVariable Long wallet_id){
        List<TransactionResponseDto> transactions = transactionService.getTransactions(wallet_id);
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK.value(), "Succeed get wallet's transactions with id " + wallet_id, transactions)
        );
    }

}
