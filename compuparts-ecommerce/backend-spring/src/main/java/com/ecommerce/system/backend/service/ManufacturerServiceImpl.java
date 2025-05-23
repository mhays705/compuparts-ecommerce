package com.ecommerce.system.backend.service;

import com.ecommerce.system.backend.dto.manufacturer.CreateManufacturerRequest;
import com.ecommerce.system.backend.dto.manufacturer.ManufacturerResponse;
import com.ecommerce.system.backend.entity.Manufacturer;
import com.ecommerce.system.backend.exception.InvalidUsernameException;
import com.ecommerce.system.backend.exception.ManufacturerNotFoundException;
import com.ecommerce.system.backend.exception.NoManufacturersFoundException;
import com.ecommerce.system.backend.mapper.ManufacturerMapper;
import com.ecommerce.system.backend.repository.ManufacturerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {


	private final ManufacturerRepository manufacturerRepository;
	private final ManufacturerMapper mapper;


	public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository, ManufacturerMapper mapper) {
		this.manufacturerRepository = manufacturerRepository;
		this.mapper = mapper;
	}


	@Override
	public List<ManufacturerResponse> findAllManufacturers() {
		List<Manufacturer> manufacturers = manufacturerRepository.findAll();
		if (manufacturers.isEmpty()) {
			throw new NoManufacturersFoundException("No manufacturers found in system");
		}
		return mapper.toDTOList(manufacturers);
	}

	@Override
	public ManufacturerResponse findManufacturerById(Long id) {

		Manufacturer manufacturer = manufacturerRepository.findById(id)
				.orElseThrow(() -> new ManufacturerNotFoundException("Manufacturer with id: " + id + " not found"));
		return mapper.toDTO(manufacturer);
	}

	@Override
	public ManufacturerResponse createManufacturer(CreateManufacturerRequest request) {

	boolean duplicate =	manufacturerRepository.findAll()
			.stream()
			.anyMatch(manufacturer -> manufacturer.getName().equalsIgnoreCase(request.getName()));

	if (duplicate) {
		throw new IllegalArgumentException("Manufacturer already exists in system");
	}

	Manufacturer manufacturer= mapper.toEntity(request);

	Manufacturer newManufacturer = manufacturerRepository.save(manufacturer);

	return mapper.toDTO(newManufacturer);

	}

	@Override
	public void deleteManufacturer(Long id) {
		if (!manufacturerRepository.existsById(id)) {
			throw new ManufacturerNotFoundException("Manufacturer with id: " + id + " not found");
		}
		manufacturerRepository.deleteById(id);
	}
}
