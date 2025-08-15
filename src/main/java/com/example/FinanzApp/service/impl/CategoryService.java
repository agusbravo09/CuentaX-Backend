package com.example.FinanzApp.service.impl;

import com.example.FinanzApp.dto.categories.CategoryDTO;
import com.example.FinanzApp.dto.categories.CreateCategoryDTO;
import com.example.FinanzApp.model.Category;
import com.example.FinanzApp.repository.ICategoryRepository;
import com.example.FinanzApp.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository catRepo;

    @Override
    public List<CategoryDTO> getAll() {
        return catRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getById(Long id) {
        Category c = catRepo.findById(id).orElseThrow( () -> new NoSuchElementException("Category not found: " + id));
        return toDTO(c);
    }

    @Override
    public CategoryDTO create(CreateCategoryDTO dto) {
        Category c = new Category();
        c.setName(dto.getName());
        catRepo.save(c);
        return toDTO(c);
    }

    @Override
    public CategoryDTO update(Long id, CreateCategoryDTO dto) {
        Category c = catRepo.findById(id).orElseThrow( () -> new NoSuchElementException("Category not found: " + id));
        if(dto.getName() != null){
            c.setName(dto.getName());
        }
        catRepo.save(c);
        return toDTO(c);
    }

    @Override
    public void delete(Long id) {
        if(!catRepo.existsById(id)){
            throw new NoSuchElementException("Category not found: " + id);
        }
        catRepo.deleteById(id);
    }

    private CategoryDTO toDTO(Category c){
        return new CategoryDTO(
                c.getId(),
                c.getName()
        );
    }
}
