package com.example.FinanzApp.service.impl;

import com.example.FinanzApp.dto.services.ServicesRequestDTO;
import com.example.FinanzApp.dto.services.ServicesResponseDTO;
import com.example.FinanzApp.mappers.ServicesMapper;
import com.example.FinanzApp.model.Account;
import com.example.FinanzApp.model.ServiceCategory;
import com.example.FinanzApp.model.Services;
import com.example.FinanzApp.repository.IAccountRepository;
import com.example.FinanzApp.repository.IServiceCategoryRepository;
import com.example.FinanzApp.repository.IServicesRepository;
import com.example.FinanzApp.service.IServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicesServiceImpl implements IServicesService {
    @Autowired
    private IServicesRepository serviceRepo;
    @Autowired
    private IAccountRepository accountRepo;
    @Autowired
    private IServiceCategoryRepository serviceCategoryRepo;
    @Autowired
    private ServicesMapper servicesMapper;

    @Override
    @Transactional
    public ServicesResponseDTO createService(ServicesRequestDTO requestDTO) {
        Services service = servicesMapper.toEntity(requestDTO);

        Account account = accountRepo.findById(requestDTO.getAccountId()).orElseThrow(() -> new RuntimeException("Account not found"));

        ServiceCategory category = serviceCategoryRepo.findById(requestDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));

        service.setAccount(account);
        service.setCategory(category);

        Services savedService = serviceRepo.save(service);
        return servicesMapper.toResponse(savedService);
    }

    @Override
    public ServicesResponseDTO getServiceById(Long id) {
        Services service = serviceRepo.findById(id).orElseThrow(() -> new RuntimeException("Service not found"));
        return servicesMapper.toResponse(service);
    }

    @Override
    public List<ServicesResponseDTO> getAllServices() {
        return serviceRepo.findAll().stream().map(servicesMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ServicesResponseDTO updateService(Long id, ServicesRequestDTO requestDTO) {
        Services existingService = serviceRepo.findById(id).orElseThrow(() -> new RuntimeException("Service not found"));

        Account account = accountRepo.findById(requestDTO.getAccountId()).orElseThrow(() -> new RuntimeException("Account not found"));

        ServiceCategory category = serviceCategoryRepo.findById(requestDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));

        existingService.setName(requestDTO.getName());
        existingService.setMonthlyAmount(requestDTO.getMonthlyAmount());
        existingService.setExpireDate(requestDTO.getExpireDate());
        existingService.setAccount(account);
        existingService.setCategory(category);

        Services updatedService = serviceRepo.save(existingService);

        return servicesMapper.toResponse(updatedService);
    }

    @Override
    @Transactional
    public void deleteService(Long id) {
        Services service = serviceRepo.findById(id).orElseThrow(() -> new RuntimeException("Service not found"));
        serviceRepo.delete(service);
    }
}
