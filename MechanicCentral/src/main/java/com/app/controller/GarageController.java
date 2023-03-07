package com.app.controller;

import java.util.List;

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
import com.app.pojos.Garage;
import com.app.service.GarageService;

@RestController
@RequestMapping("/garage")
public class GarageController {

	@Autowired
	private GarageService garageservice;
	
	

	public GarageController() {
		super();
	}

	@PostMapping("/accept/{garageId}/appointmentreqId/{appreqId}")
	public ResponseEntity<AppointmentResponsedto> AcceptAppointment(@PathVariable Long garageId,
			@PathVariable Long appreqId) {
		garageservice.AcceptAppointment(garageId, appreqId);
		return null;

	}

	@PutMapping("/decline/{garageId}")
	public ResponseEntity<AppointmentResponsedto> DeclineAppointment(@PathVariable Long garageId,
			@RequestBody AppointmentRequestdto appointreq) {
		garageservice.DeclineAppointment(garageId, appointreq);
		return null;

	}

	@GetMapping("/{garageId}")
	public ResponseEntity<Garagedto> getGarageById(@PathVariable Long garageId) {

		return ResponseEntity.ok(garageservice.getGarageById(garageId));
	}
	
	@GetMapping("/appointmentrequest/{garageId}")
	public ResponseEntity<List<AppointmentResponsedto>> getAllRequestedAppointments(@PathVariable Long garageId){
		Garage garg=new Garage();
		System.out.println(garg.getAppointmentRequests());
		return ResponseEntity.ok(garageservice.getAllRequestAppointment(garageId));
	}

}
