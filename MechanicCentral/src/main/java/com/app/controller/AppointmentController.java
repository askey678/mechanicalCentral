package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AppointmentRequestdto;
import com.app.dto.AppointmentResponsedto;
import com.app.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping("/customer/{customerId}")
	public ResponseEntity<?> createAppointment(@PathVariable Long customerId,
			@RequestBody AppointmentRequestdto appointmentRequest) {
		System.out.println(appointmentRequest);
		try {
			AppointmentResponsedto appointmentresponse = appointmentService.bookAppointment(customerId,
					appointmentRequest);
			return ResponseEntity.ok(appointmentresponse);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

//	@GetMapping("/{appointmentId}")
//	public ResponseEntity<?> getAppointment(@PathVariable Long appointmentId) {
//		try {
//			Appointment appointment = appointmentService.getAppointmentById(appointmentId);
//			return ResponseEntity.ok(new AppointmentResponse(appointment));
//		} catch (RuntimeException e) {
//			return ResponseEntity.notFound().build();
//		}
//	}
//
//	@PutMapping("/{appointmentId}")
//	public ResponseEntity<?> updateAppointment(@PathVariable int appointmentId, @RequestBody AppointmentRequest appointmentRequest) {
//		try {
//			Appointment appointment = appointmentService.updateAppointment(appointmentId, appointmentRequest);
//			return ResponseEntity.ok(new AppointmentResponse(appointment));
//		} catch (RuntimeException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//	}
//
//	@DeleteMapping("/{appointmentId}")
//	public ResponseEntity<?> deleteAppointment(@PathVariable int appointmentId) {
//		try {
//			appointmentService.deleteAppointment(appointmentId);
//			return ResponseEntity.ok().build();
//		} catch (RuntimeException e) {
//			return ResponseEntity.notFound().build();
//		}
//	}
}
