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
import com.app.dto.Packagedto;
import com.app.service.PackageService;

@RestController
@RequestMapping("/package")
public class PackageController {

	@Autowired
	private PackageService packageservice;

	public PackageController() {
		super();
	}

	@PostMapping("/")
	public ResponseEntity<Packagedto> AddPackage(@RequestBody Packagedto pkgdto) {
		Packagedto pkg = packageservice.addPackage(pkgdto);
		return new ResponseEntity<Packagedto>(pkg, HttpStatus.CREATED);

	}

	@GetMapping("/")
	public ResponseEntity<List<Packagedto>> getAllPackages() {
		return ResponseEntity.ok(packageservice.getAllPackages());

	}

	@GetMapping("/{packageId}")
	public ResponseEntity<Packagedto> getPackageById(@PathVariable Long packageId) {
		return ResponseEntity.ok(packageservice.getPackageById(packageId));

	}

	@PutMapping("/{packageId}")
	public ResponseEntity<Packagedto> updatePackage(@RequestBody Packagedto pkgdto, @PathVariable Long packageId) {

		return ResponseEntity.ok(packageservice.updatePackage(pkgdto, packageId));
	}

	@DeleteMapping("/{packageId}")
	public ResponseEntity<ApiResponse> deletePackage(@PathVariable Long id) {
		packageservice.deletePackage(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Package deleted successfully", true), HttpStatus.OK);

	}

}
