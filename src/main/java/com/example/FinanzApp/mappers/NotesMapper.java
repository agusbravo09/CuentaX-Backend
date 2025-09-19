package com.example.FinanzApp.mappers;

import com.example.FinanzApp.dto.notes.NotesRequestDTO;
import com.example.FinanzApp.dto.notes.NotesResponseDTO;
import com.example.FinanzApp.model.Notes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NotesMapper {
    NotesMapper INSTANCE = Mappers.getMapper(NotesMapper.class);

    Notes toEntity(NotesRequestDTO requestDTO);

    @Mapping(source = "user.name", target = "userName")
    NotesResponseDTO toResponse(Notes entity);
}
