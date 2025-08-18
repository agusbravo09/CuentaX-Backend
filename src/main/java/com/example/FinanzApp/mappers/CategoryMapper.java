package com.example.FinanzApp.mappers;

import com.example.FinanzApp.dto.category.CategoryDTO;
import com.example.FinanzApp.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toEntity(CategoryDTO dto);
    CategoryDTO toResponse(Category entity);
}
