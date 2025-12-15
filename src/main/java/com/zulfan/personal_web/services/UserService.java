package com.zulfan.personal_web.services;

import com.zulfan.personal_web.dto.UserRequestDto;
import com.zulfan.personal_web.dto.UserResponseDto;
import com.zulfan.personal_web.entities.User;
import com.zulfan.personal_web.exceptions.DuplicateResourceException;
import com.zulfan.personal_web.exceptions.ResourceNotFoundException;
import com.zulfan.personal_web.mapper.UserMapper;
import com.zulfan.personal_web.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserMapper userMapper;


    public UserResponseDto getUserById(Long id){
        User user = userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.toDtoResponse(user);
    }

    public UserResponseDto createNewUser(UserRequestDto userDto){
        if(userRepository.existsByEmail(userDto.email())){
            throw new DuplicateResourceException("email", "Email already exist");
        }
        if(userRepository.existsByUsername(userDto.username())){
            throw new DuplicateResourceException("username","Username already exist");
        }
        User newUser = userMapper.toEntity(userDto);
        User saveUser = userRepository.save(newUser);
        return userMapper.toDtoResponse(saveUser);
    }

    public UserResponseDto updateUser(Long id, UserRequestDto userDto){
        User user = userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        if(userDto.username() != null){
            user.setUsername(userDto.username());
        }

        if(userDto.email() != null){
            user.setEmail(userDto.email());
        }

        User updatedUser = userRepository.save(user);
        return userMapper.toDtoResponse(updatedUser);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
