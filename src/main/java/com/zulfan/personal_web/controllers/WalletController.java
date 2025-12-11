package com.zulfan.personal_web.controllers;

import com.zulfan.personal_web.dto.WalletCreateDto;
import com.zulfan.personal_web.entities.Wallet;
import com.zulfan.personal_web.services.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @PostMapping("users/{user_id}/wallet")
    public ResponseEntity<Wallet> createWallet(@PathVariable Long user_id, @Valid @RequestBody WalletCreateDto wallet) {
        Wallet newWallet = walletService.createWallet(user_id, wallet);
        return ResponseEntity.ok(newWallet);
    }

    @GetMapping("users/{user_id}/wallet")
    public ResponseEntity<Map> getUserWallet(@PathVariable Long user_id){
        List wallets = walletService.getUserWallets(user_id);
        return ResponseEntity.ok(
                Map.of(
                        "status", 200,
                        "message", "Succeed get user's wallets with id " + user_id,
                        "data", wallets
                        )
        );
    }

    @DeleteMapping("wallet/{wallet_id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Long wallet_id){
        walletService.deleteWallet(wallet_id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("wallet/{wallet_id}")
    public ResponseEntity<Wallet> updateWallet(@PathVariable Long wallet_id, @Valid @RequestBody WalletCreateDto wallet){
        Wallet updatedWallet = walletService.updateWallet(wallet_id, wallet);
        return ResponseEntity.ok(updatedWallet);
    }
}
