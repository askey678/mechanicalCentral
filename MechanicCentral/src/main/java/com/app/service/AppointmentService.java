package com.app.service;

import java.util.List;

import com.app.dto.Appointmentdto;
import com.app.pojos.Appointment;
import com.app.pojos.AppointmentStatus;

public interface AppointmentService {

	Appointment bookAppointment(Appointmentdto appointmentdto, Long customerId, Long garageId);

	List<Appointment> getAllAppointments();

	Appointment getAppointmentById(Long appointmentId);

	Appointment updateAppointment(Appointmentdto appointmentdto, Long appointmentId);

	void deleteAppointment(Long appointmentId);

	List<Appointment> getAppointmentsByGarage(Long garageId);

	List<Appointment> getAppointmentsByCustomer(Long customerId);

	List<Appointment> getAppointmentsByGarageAndStatus(Long garageId, AppointmentStatus status);

	List<Appointment> getAppointmentsByCustomerAndStatus(Long customerId, AppointmentStatus status);

	List<Appointment> getConfirmedAppointments();

	List<Appointment> getInProgressAppointments();

	List<Appointment> getCancelledAppointments();

}
