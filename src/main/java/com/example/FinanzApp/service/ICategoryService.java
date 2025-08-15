package com.example.FinanzApp.service;

import com.example.FinanzApp.dto.categories.CategoryDTO;
import com.example.FinanzApp.dto.categories.CreateCategoryDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> getAll();
    CategoryDTO getById(Long id);
    CategoryDTO create(CreateCategoryDTO dto);
    CategoryDTO update(Long id, CreateCategoryDTO dto);
    void delete(Long id);
}
