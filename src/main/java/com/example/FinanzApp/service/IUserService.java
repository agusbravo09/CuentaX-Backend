package com.example.FinanzApp.service;

import com.example.FinanzApp.dto.user.UserRequestDTO;
import com.example.FinanzApp.dto.user.UserResponseDTO;

import java.util.List;

public interface IUserService {
    UserResponseDTO createUser(UserRequestDTO requestDTO);
    UserResponseDTO getUserById(Long id);
    UserResponseDTO getUserByEmail(String email);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO);
    void deleteUser(Long id);
}
