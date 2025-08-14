package com.example.FinanzApp.repository;

import com.example.FinanzApp.model.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServiceCategoryRepository extends JpaRepository<ServiceCategory, Long> {
}
