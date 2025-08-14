package com.example.FinanzApp.repository;

import com.example.FinanzApp.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServiceRepository extends JpaRepository<Service, Long> {
}
