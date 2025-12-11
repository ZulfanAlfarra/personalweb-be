package com.zulfan.personal_web.services;

import com.zulfan.personal_web.dto.WalletCreateDto;
import com.zulfan.personal_web.entities.User;
import com.zulfan.personal_web.entities.Wallet;
import com.zulfan.personal_web.exceptions.ResourceNotFoundException;
import com.zulfan.personal_web.repositories.UserRepository;
import com.zulfan.personal_web.repositories.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public Wallet createWallet(Long user_id, WalletCreateDto wallet){
        Wallet newWallet = modelMapper.map(wallet, Wallet.class);
        newWallet.setBalance(BigDecimal.ZERO);
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + user_id));
        newWallet.setUser(user);
        Wallet saved = walletRepository.save(newWallet);
        return saved;
    }

    public List getUserWallets(Long user_id){
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + user_id));
        List wallets = user.getWallets();
        return wallets;
    }

    public void deleteWallet(Long wallet_id){
        walletRepository.deleteById(wallet_id);
    }

    public Wallet updateWallet(Long wallet_id, WalletCreateDto req_wallet){
        Wallet wallet = walletRepository.findById(wallet_id).orElseThrow(()-> new ResourceNotFoundException("Wallet not found with id " + wallet_id));
        if (req_wallet.getName() != null){
            wallet.setName(req_wallet.getName());
        }
        wallet.setUpdatedAt(LocalDateTime.now());
        Wallet savedWallet = walletRepository.save(wallet);
        return wallet;
    }
}
