package com.zulfan.personal_web.services;

import com.zulfan.personal_web.dto.WalletRequestDto;
import com.zulfan.personal_web.dto.WalletResponseDto;
import com.zulfan.personal_web.dto.WalletSummaryDto;
import com.zulfan.personal_web.entities.User;
import com.zulfan.personal_web.entities.Wallet;
import com.zulfan.personal_web.exceptions.DuplicateResourceException;
import com.zulfan.personal_web.exceptions.ResourceNotFoundException;
import com.zulfan.personal_web.mapper.WalletMapper;
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
    private final WalletMapper walletMapper;

//    public Wallet createWallet(Long user_id, WalletRequestDto wallet){
//        Wallet newWallet = modelMapper.map(wallet, Wallet.class);
//        newWallet.setBalance(BigDecimal.ZERO);
//        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + user_id));
//        newWallet.setUser(user);
//        Wallet saved = walletRepository.save(newWallet);
//        return saved;
//    }

    public WalletResponseDto createWallet(Long user_id, WalletRequestDto dtoReq){
        if(walletRepository.existsByUserIdAndName(user_id, dtoReq.name())) {
            throw new DuplicateResourceException("name" ,"Wallet name already exist for this user");
        }

        User user = userRepository.findById(user_id).orElseThrow(()-> new ResourceNotFoundException("User not found with id " + user_id));
        Wallet wallet = walletMapper.toEntity(dtoReq);
        wallet.setUser(user);
        wallet.setBalance(BigDecimal.ZERO);
        Wallet saved = walletRepository.save(wallet);
        return walletMapper.toDtoResponse(saved);
    }

    public List<WalletSummaryDto> getUserWallets(Long user_id){
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + user_id));
        return user.getWallets()
                .stream()
                .map(walletMapper::toDtoSummary)
                .toList();
    }


    public void deleteWallet(Long wallet_id){
        walletRepository.deleteById(wallet_id);
    }

    public WalletResponseDto updateWallet(Long wallet_id, WalletRequestDto dtoReq){
        Wallet wallet = walletRepository.findById(wallet_id).orElseThrow(()-> new ResourceNotFoundException("Wallet not found with id " + wallet_id));

        if (dtoReq.name() != null ){
            String newName = dtoReq.name().trim();

            if(walletRepository.existsByUserIdAndName(wallet.getUser().getId(), newName)){
                throw new DuplicateResourceException("name", "wallet name already exist for this user");
            }

            wallet.setName(newName);

        }

        Wallet savedWallet = walletRepository.save(wallet);
        return walletMapper.toDtoResponse(savedWallet);
    }
}
