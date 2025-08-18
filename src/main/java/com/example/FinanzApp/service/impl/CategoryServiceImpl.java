package com.example.FinanzApp.service.impl;

import com.example.FinanzApp.dto.category.CategoryDTO;
import com.example.FinanzApp.mappers.CategoryMapper;
import com.example.FinanzApp.model.Category;
import com.example.FinanzApp.repository.ICategoryRepository;
import com.example.FinanzApp.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepo;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        if(categoryRepo.existsByNameIgnoreCase(categoryDTO.getName())){
            throw new RuntimeException("Category name already exists");
        }

        Category category = categoryMapper.toEntity(categoryDTO);
        Category savedCategory = categoryRepo.save(category);
        return categoryMapper.toResponse(savedCategory);
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));

        return categoryMapper.toResponse(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepo.findAll().stream().map(categoryMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));

        if(!existingCategory.getName().equals(categoryDTO.getName())){
            if(categoryRepo.existsByNameIgnoreCase(categoryDTO.getName())){
                throw new RuntimeException("Category name already exists");
            }
        }

        existingCategory.setName(categoryDTO.getName());
        Category updatedCategory = categoryRepo.save(existingCategory);
        return categoryMapper.toResponse(updatedCategory);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));

        if(!category.getTransactions().isEmpty()){
            throw new RuntimeException("Cannot delete category with associated transactions");
        }

        categoryRepo.delete(category);
    }

}
