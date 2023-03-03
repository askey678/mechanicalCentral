package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.Servicedto;
import com.app.service.ServingService;

@RestController
@RequestMapping("/service")
public class ServiceController {

	@Autowired
	private ServingService servingservice;

	public ServiceController() {
		super();
	}

	@PostMapping("/")
	public ResponseEntity<Servicedto> addService(@RequestBody Servicedto servicedto) {

		Servicedto service = servingservice.addservice(servicedto);
		return new ResponseEntity<Servicedto>(service, HttpStatus.CREATED);

	}

	@GetMapping("/")
	public ResponseEntity<List<Servicedto>> getAllServices() {
		return ResponseEntity.ok(servingservice.getAllServices());
	}

	@GetMapping("/{serviceId}")
	public ResponseEntity<Servicedto> getServiceById(@PathVariable Long serviceId) {
		return ResponseEntity.ok(servingservice.getServiceById(serviceId));
	}

	@PutMapping("/{serviceId}")
	public ResponseEntity<Servicedto> updateService(@RequestBody Servicedto servicedto, @PathVariable Long serviceId) {
		Servicedto updatedservice = servingservice.updateService(servicedto, serviceId);
		return ResponseEntity.ok(updatedservice);
	}

	@DeleteMapping("/{serviceId}")
	public ResponseEntity<ApiResponse> deleteService(@PathVariable Long serviceId) {
		servingservice.deleteService(serviceId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Service deleted successfully", true), HttpStatus.OK);
	}

	
}
