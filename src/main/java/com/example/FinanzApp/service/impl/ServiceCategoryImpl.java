package com.example.FinanzApp.service.impl;

import com.example.FinanzApp.dto.serviceCategory.ServiceCategoryDTO;
import com.example.FinanzApp.mappers.ServiceCategoryMapper;
import com.example.FinanzApp.model.ServiceCategory;
import com.example.FinanzApp.repository.IServiceCategoryRepository;
import com.example.FinanzApp.service.IServiceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceCategoryImpl implements IServiceCategoryService {
    @Autowired
    private IServiceCategoryRepository serviceCategoryRepo;
    @Autowired
    private ServiceCategoryMapper Mapper;

    @Override
    public ServiceCategoryDTO createServiceCategory(ServiceCategoryDTO dto) {
        ServiceCategory serviceCategory = new ServiceCategory();
        serviceCategory.setName(dto.getName());

        ServiceCategory savedCategory = serviceCategoryRepo.save(serviceCategory);

        return Mapper.toResponse(savedCategory);
    }

    @Override
    public ServiceCategoryDTO getServiceCategoryById(Long id) {
        ServiceCategory serviceCategory = serviceCategoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Service Category not found"));
        return Mapper.toResponse(serviceCategory);
    }

    @Override
    public List<ServiceCategoryDTO> getAllServiceCategories() {
        return serviceCategoryRepo.findAll().stream().map(Mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ServiceCategoryDTO updateServiceCategory(Long id, ServiceCategoryDTO dto) {
        ServiceCategory existingCategory = serviceCategoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Service category not found"));
        existingCategory.setName(dto.getName());

        ServiceCategory updatedCategory = serviceCategoryRepo.save(existingCategory);
        return Mapper.toResponse(updatedCategory);
    }

    @Override
    @Transactional
    public void deleteServiceCategory(Long id) {
        ServiceCategory serviceCategory = serviceCategoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Service category not found"));

        if(!serviceCategory.getServices().isEmpty()){
            throw new RuntimeException("Cannot  delete category with associated services");
        }

        serviceCategoryRepo.delete(serviceCategory);
    }
}
