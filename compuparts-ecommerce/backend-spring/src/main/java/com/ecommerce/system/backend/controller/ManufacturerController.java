package com.ecommerce.system.backend.controller;


import com.ecommerce.system.backend.dto.manufacturer.CreateManufacturerRequest;
import com.ecommerce.system.backend.dto.manufacturer.ManufacturerResponse;
import com.ecommerce.system.backend.service.ManufacturerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerController {


	private final ManufacturerService manufacturerService;

	public ManufacturerController(ManufacturerService manufacturerService) {
		this.manufacturerService = manufacturerService;
	}

	@GetMapping()
	public ResponseEntity<List<ManufacturerResponse>> findAllManufacturers() {
		List<ManufacturerResponse> manufacturers = manufacturerService.findAllManufacturers();
		return ResponseEntity.ok(manufacturers);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ManufacturerResponse> findManufacturerById(@PathVariable Long id) {
		ManufacturerResponse manufacturer = manufacturerService.findManufacturerById(id);
		return ResponseEntity.ok(manufacturer);
	}

	@PostMapping()
	public ResponseEntity<ManufacturerResponse> createManufacturer(@Valid @RequestBody CreateManufacturerRequest request) {
		ManufacturerResponse newManufacturer = manufacturerService.createManufacturer(request);
		URI location = URI.create("/api/manufacturers/" + newManufacturer.getId());
		return ResponseEntity.created(location).body(newManufacturer);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteManufacturer(@PathVariable Long id) {
		manufacturerService.deleteManufacturer(id);
		return ResponseEntity.noContent().build();
	}


}
