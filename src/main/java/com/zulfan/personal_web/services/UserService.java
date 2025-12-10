package com.zulfan.personal_web.services;

import com.zulfan.personal_web.dto.UserDto;
import com.zulfan.personal_web.dto.UserResponse;
import com.zulfan.personal_web.entities.User;
import com.zulfan.personal_web.exceptions.DuplicateResourceException;
import com.zulfan.personal_web.exceptions.ResourceNotFoundException;
import com.zulfan.personal_web.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public UserResponse getUserById(Long id){
        User user = userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return modelMapper.map(user, UserResponse.class);
    }

    public UserResponse createNewUser(UserDto userDto){
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new DuplicateResourceException("email", "Email already exist");
        }
        if(userRepository.existsByUsername(userDto.getUsername())){
            throw new DuplicateResourceException("username","Username already exist");
        }
        User newUser = modelMapper.map(userDto, User.class);
        User saveUser = userRepository.save(newUser);
        return modelMapper.map(saveUser, UserResponse.class);
    }

    public UserResponse updateUser(Long id, UserDto userDto){
        User user = userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        if(userDto.getUsername() != null){
            user.setUsername(userDto.getUsername());
        }

        if(userDto.getEmail() != null){
            user.setEmail(userDto.getEmail());
        }

        user.setUpdatedAt(LocalDateTime.now());

        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserResponse.class);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
