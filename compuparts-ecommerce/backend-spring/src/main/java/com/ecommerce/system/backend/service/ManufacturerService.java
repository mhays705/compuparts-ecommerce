package com.ecommerce.system.backend.service;

import com.ecommerce.system.backend.dto.manufacturer.CreateManufacturerRequest;
import com.ecommerce.system.backend.dto.manufacturer.ManufacturerResponse;

import java.util.List;

public interface ManufacturerService {

	List<ManufacturerResponse> findAllManufacturers();

	ManufacturerResponse findManufacturerById(Long id);

	ManufacturerResponse createManufacturer(CreateManufacturerRequest request);

	void deleteManufactuer(Long id);

}
