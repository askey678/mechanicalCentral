package com.app.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.app.dto.Garagedto;
import com.app.service.GarageService;



@RestController
@RequestMapping("/garage")
public class GarageController {

	@Autowired
	private GarageService garageservice;

	public GarageController() {
		super();
	}

	@PostMapping("/")
	public ResponseEntity<Garagedto> AddGarage(@Valid @RequestBody Garagedto gargdto) {
		Garagedto garage = garageservice.addGarage(gargdto);
		return new ResponseEntity<Garagedto>(garage, HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<Garagedto>> getAllGarage() {
		return ResponseEntity.ok(garageservice.getAllGarage());
	}

	@GetMapping("/{GarageId}")
	public ResponseEntity<Garagedto> getGaragebyId(@PathVariable Long GarageId) {
		return ResponseEntity.ok(garageservice.getGarageById(GarageId));
	}

	@PutMapping("/{GarageId}")
	public ResponseEntity<Garagedto> updateGarage(@Valid @RequestBody Garagedto gargdto, @PathVariable Long GarageId) {
		Garagedto updatedgarage = garageservice.updateGarage(gargdto, GarageId);
		return ResponseEntity.ok(updatedgarage);

	}

	@DeleteMapping("/{GarageId}")
	public ResponseEntity<ApiResponse> deleteGarage(@PathVariable Long GarageId) {
		garageservice.deleteGarage(GarageId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Garage deleted successfully", true), HttpStatus.OK);

	}
}
