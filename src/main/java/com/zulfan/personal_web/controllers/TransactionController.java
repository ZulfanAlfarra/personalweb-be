package com.zulfan.personal_web.controllers;

import com.zulfan.personal_web.dto.TransactionRequestDto;
import com.zulfan.personal_web.dto.TransactionResponseDto;
import com.zulfan.personal_web.services.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("{user_id}/wallet/{wallet_id}")
    public ResponseEntity<TransactionResponseDto> createTransaction(@PathVariable Long user_id, @PathVariable Long wallet_id, @Valid @RequestBody TransactionRequestDto dto){
        TransactionResponseDto transaction = transactionService.create(wallet_id, dto);
        return ResponseEntity.ok(transaction);
    }

}
