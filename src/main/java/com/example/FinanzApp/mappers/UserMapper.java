package com.example.FinanzApp.mappers;

import com.example.FinanzApp.dto.user.UserRequestDTO;
import com.example.FinanzApp.dto.user.UserResponseDTO;
import com.example.FinanzApp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserRequestDTO dto);
    UserResponseDTO toResponse(User entity);
}
