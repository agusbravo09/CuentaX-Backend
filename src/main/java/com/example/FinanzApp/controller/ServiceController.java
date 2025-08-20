package com.example.FinanzApp.controller;

import com.example.FinanzApp.dto.services.ServicesRequestDTO;
import com.example.FinanzApp.dto.services.ServicesResponseDTO;
import com.example.FinanzApp.service.IServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
public class ServiceController {
    @Autowired
    private IServicesService serviceService;

    @PostMapping
    public ResponseEntity<ServicesResponseDTO> createService(@RequestBody ServicesRequestDTO requestDTO) {
        ServicesResponseDTO response = serviceService.createService(requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicesResponseDTO> getServiceById(@PathVariable Long id) {
        ServicesResponseDTO response = serviceService.getServiceById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServicesResponseDTO>> getAllServices() {
        List<ServicesResponseDTO> responses = serviceService.getAllServices();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicesResponseDTO> updateService(
            @PathVariable Long id,
            @RequestBody ServicesRequestDTO requestDTO) {
        ServicesResponseDTO response = serviceService.updateService(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
