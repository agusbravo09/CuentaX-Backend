package com.example.FinanzApp.service;

import com.example.FinanzApp.dto.users.CreateUserDTO;
import com.example.FinanzApp.dto.users.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAll();
    UserDTO getById(Long id);
    UserDTO create(CreateUserDTO dto);
    UserDTO updateEmail(Long id, String email);
    void delete(Long id);
}
