package com.inventorymanager.backend.mapper;

import com.inventorymanager.backend.dto.manufacturer.CreateManufacturerRequest;
import com.inventorymanager.backend.dto.manufacturer.ManufacturerResponse;
import com.inventorymanager.backend.entity.Manufacturer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ManufacturerMapper {


	Manufacturer toEntity(CreateManufacturerRequest createManufacturerRequest);

	ManufacturerResponse toDTO(Manufacturer manufacturer);

	List<Manufacturer> toEntityList(List<CreateManufacturerRequest> createManufacturerRequests);

	List<ManufacturerResponse> toDTOList(List<Manufacturer> manufacturers);

}
