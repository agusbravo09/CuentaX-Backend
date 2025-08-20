package com.example.FinanzApp.controller;

import com.example.FinanzApp.dto.serviceCategory.ServiceCategoryDTO;
import com.example.FinanzApp.service.IServiceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/service-categories")
public class ServiceCategoryController {
    @Autowired
    private IServiceCategoryService serviceCategoryService;

    @PostMapping
    public ResponseEntity<ServiceCategoryDTO> createServiceCategory(@RequestBody ServiceCategoryDTO serviceCategoryDTO) {
        ServiceCategoryDTO response = serviceCategoryService.createServiceCategory(serviceCategoryDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceCategoryDTO> getServiceCategoryById(@PathVariable Long id) {
        ServiceCategoryDTO response = serviceCategoryService.getServiceCategoryById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServiceCategoryDTO>> getAllServiceCategories() {
        List<ServiceCategoryDTO> responses = serviceCategoryService.getAllServiceCategories();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceCategoryDTO> updateServiceCategory(
            @PathVariable Long id,
            @RequestBody ServiceCategoryDTO serviceCategoryDTO) {
        ServiceCategoryDTO response = serviceCategoryService.updateServiceCategory(id, serviceCategoryDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceCategory(@PathVariable Long id) {
        serviceCategoryService.deleteServiceCategory(id);
        return ResponseEntity.noContent().build();
    }
}
