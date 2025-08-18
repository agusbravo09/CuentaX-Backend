package com.example.FinanzApp.service.impl;

import com.example.FinanzApp.dto.user.UserRequestDTO;
import com.example.FinanzApp.dto.user.UserResponseDTO;
import com.example.FinanzApp.mappers.UserMapper;
import com.example.FinanzApp.model.User;
import com.example.FinanzApp.repository.IUserRepository;
import com.example.FinanzApp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepo;
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        if(userRepo.existsByEmail(requestDTO.getEmail())){
            throw new RuntimeException("Email already in use.");
        }

        User user = userMapper.toEntity(requestDTO);
        User savedUser = userRepo.save(user);

        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepo.findByEmail(email);

        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepo.findAll().stream().map(userMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO) {
        User existingUser = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        if(!existingUser.getEmail().equals(requestDTO.getEmail())){
            if(userRepo.existsByEmail(requestDTO.getEmail())){
                throw new RuntimeException("Email already in use");
            }
        }

        existingUser.setName(requestDTO.getName());
        existingUser.setEmail(requestDTO.getEmail());
        existingUser.setPassword(requestDTO.getPassword());

        User updatedUser = userRepo.save(existingUser);
        return userMapper.toResponse(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepo.delete(user);
    }
}
