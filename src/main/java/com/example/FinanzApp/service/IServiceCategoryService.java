package com.example.FinanzApp.service;

import com.example.FinanzApp.dto.services.CreateServiceCategoryDTO;
import com.example.FinanzApp.dto.services.ServiceCategoryDTO;

import java.util.List;

public interface IServiceCategoryService {
    List<ServiceCategoryDTO> getAll();
    ServiceCategoryDTO getById(Long id);
    ServiceCategoryDTO create(CreateServiceCategoryDTO dto);
    ServiceCategoryDTO update(Long id, CreateServiceCategoryDTO dto);
    void delete(Long id);
}
