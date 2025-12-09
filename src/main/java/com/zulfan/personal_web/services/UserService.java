package com.zulfan.personal_web.services;

import com.zulfan.personal_web.dto.UserDto;
import com.zulfan.personal_web.dto.UserResponse;
import com.zulfan.personal_web.entities.User;
import com.zulfan.personal_web.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public UserResponse getUserById(Long id){
        User user = userRepository.findById(id).orElse(null);
        return modelMapper.map(user, UserResponse.class);
    }

    public UserResponse createNewUser(UserDto userDto){
        User newUser = modelMapper.map(userDto, User.class);
        User saveUser = userRepository.save(newUser);
        return modelMapper.map(saveUser, UserResponse.class);
    }

    public UserDto updateUser(Long id, UserDto userDto){
        User user = userRepository.findById(id).orElse(null);
        modelMapper.map(userDto, user);
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }


}
