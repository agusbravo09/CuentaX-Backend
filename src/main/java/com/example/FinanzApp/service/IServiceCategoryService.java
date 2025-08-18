package com.example.FinanzApp.service;

import com.example.FinanzApp.dto.serviceCategory.ServiceCategoryDTO;

import java.util.List;

public interface IServiceCategoryService {
    ServiceCategoryDTO createServiceCategory(ServiceCategoryDTO dto);
    ServiceCategoryDTO getServiceCategoryById(Long id);
    List<ServiceCategoryDTO> getAllServiceCategories();
    ServiceCategoryDTO updateServiceCategory(Long id, ServiceCategoryDTO dto);
    void deleteServiceCategory(Long id);
}
