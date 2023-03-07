package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AppointmentRequestdto;
import com.app.dto.AppointmentResponsedto;
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

	@PostMapping("/{garageId}")
	public ResponseEntity<AppointmentResponsedto> AcceptAppointment(@PathVariable Long garageId,
			@RequestBody AppointmentRequestdto appointreq) {
		garageservice.AcceptAppointment(garageId, appointreq);
		return null;

	}

	@PutMapping("/{garageId}")
	public ResponseEntity<AppointmentResponsedto> DeclineAppointment(@PathVariable Long garageId,
			@RequestBody AppointmentRequestdto appointreq) {
		garageservice.DeclineAppointment(garageId, appointreq);
		return null;

	}

	@GetMapping("/{garageId}")
	public ResponseEntity<Garagedto> getGarageById(@PathVariable Long garageId) {

		return ResponseEntity.ok(garageservice.getGarageById(garageId));
	}

}
