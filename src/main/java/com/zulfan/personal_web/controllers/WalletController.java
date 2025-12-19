package com.zulfan.personal_web.controllers;

import com.zulfan.personal_web.dto.ApiResponse;
import com.zulfan.personal_web.dto.WalletRequestDto;
import com.zulfan.personal_web.dto.WalletResponseDto;
import com.zulfan.personal_web.entities.Wallet;
import com.zulfan.personal_web.services.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @PostMapping("{user_id}/wallet")
    public ResponseEntity<?> createWallet(@PathVariable Long user_id, @Valid @RequestBody WalletRequestDto wallet) {
        WalletResponseDto newWallet = walletService.createWallet(user_id, wallet);
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.CREATED.value(), "Succeed create wallet", newWallet)
        );
    }

    @GetMapping("{user_id}/wallet")
    public ResponseEntity<?> getUserWallet(@PathVariable Long user_id){
        List wallets = walletService.getUserWallets(user_id);
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK.value(), "Succeed get user's wallets with id " + user_id, wallets)
        );
    }

    @GetMapping("wallet/{wallet_id}/range")
    public ResponseEntity<?> getUserWalletTransactionSummary(@PathVariable Long wallet_id, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
        Map<String, Object> summary = walletService.getWalletSummaryRange(wallet_id, startDate, endDate);
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK.value(), "Succeed get wallet transaction range " + startDate + " - " + endDate, summary)
        );
    }

    @DeleteMapping("wallet/{wallet_id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Long wallet_id){
        walletService.deleteWallet(wallet_id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{user_id}/wallet/{wallet_id}")
    public ResponseEntity<?> updateWallet(@PathVariable Long wallet_id, @Valid @RequestBody WalletRequestDto wallet){
        WalletResponseDto updatedWallet = walletService.updateWallet(wallet_id, wallet);
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK.value(), "Succeed update wallet with id " + wallet_id, updatedWallet)
        );
    }
}
