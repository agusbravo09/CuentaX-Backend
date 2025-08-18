package com.example.FinanzApp.service;

import com.example.FinanzApp.dto.services.ServicesRequestDTO;
import com.example.FinanzApp.dto.services.ServicesResponseDTO;

import java.util.List;

public interface IServicesService {
    ServicesResponseDTO createService(ServicesRequestDTO requestDTO);
    ServicesResponseDTO getServiceById(Long id);
    List<ServicesResponseDTO> getAllServices();
    ServicesResponseDTO updateService(Long id, ServicesRequestDTO requestDTO);
    void deleteService(Long id);
}
