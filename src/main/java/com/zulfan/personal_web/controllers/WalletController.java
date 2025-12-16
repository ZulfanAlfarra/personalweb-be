package com.zulfan.personal_web.controllers;

import com.zulfan.personal_web.dto.WalletRequestDto;
import com.zulfan.personal_web.dto.WalletResponseDto;
import com.zulfan.personal_web.entities.Wallet;
import com.zulfan.personal_web.services.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @PostMapping("{user_id}/wallet")
    public ResponseEntity<WalletResponseDto> createWallet(@PathVariable Long user_id, @Valid @RequestBody WalletRequestDto wallet) {
        WalletResponseDto newWallet = walletService.createWallet(user_id, wallet);
        return ResponseEntity.ok(newWallet);
    }

    @GetMapping("{user_id}/wallet")
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

    @PutMapping("{user_id}/wallet/{wallet_id}")
    public ResponseEntity<WalletResponseDto> updateWallet(@PathVariable Long wallet_id, @Valid @RequestBody WalletRequestDto wallet){
        WalletResponseDto updatedWallet = walletService.updateWallet(wallet_id, wallet);
        return ResponseEntity.ok(updatedWallet);
    }
}
