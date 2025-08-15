package com.example.FinanzApp.service;

import com.example.FinanzApp.dto.services.CreateServiceDTO;
import com.example.FinanzApp.dto.services.ServiceDTO;

import java.util.List;

public interface IServiceService {
    List<ServiceDTO> getAll();
    ServiceDTO getById(Long id);
    ServiceDTO create(CreateServiceDTO dto);
    ServiceDTO update(Long id, CreateServiceDTO dto);
    void delete(Long id);
}
