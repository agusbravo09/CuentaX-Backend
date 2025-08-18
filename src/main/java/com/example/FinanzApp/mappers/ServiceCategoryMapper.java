package com.example.FinanzApp.mappers;

import com.example.FinanzApp.dto.serviceCategory.ServiceCategoryDTO;
import com.example.FinanzApp.model.ServiceCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ServiceCategoryMapper {
    ServiceCategoryMapper INSTANCE = Mappers.getMapper(ServiceCategoryMapper.class);

    ServiceCategoryDTO toResponse(ServiceCategory entity);
}
