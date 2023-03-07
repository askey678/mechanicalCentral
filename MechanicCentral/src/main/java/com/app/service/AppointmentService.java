package com.app.service;

import java.util.List;

import com.app.dto.AppointmentRequestdto;
import com.app.dto.AppointmentResponsedto;
import com.app.pojos.Appointment;
import com.app.pojos.AppointmentStatus;

public interface AppointmentService {

	AppointmentResponsedto bookAppointment(Long customerId, AppointmentRequestdto appointmentdto);

	List<Appointment> getAllAppointments();

	Appointment getAppointmentById(Long appointmentId);

	AppointmentResponsedto updateAppointment(AppointmentRequestdto appointmentdto, Long appointmentId);

	void deleteAppointment(Long appointmentId);

	List<Appointment> getAppointmentsByGarage(Long garageId);

	List<Appointment> getAppointmentsByCustomer(Long customerId);

	List<Appointment> getAppointmentsByGarageAndStatus(Long garageId, AppointmentStatus status);

	List<Appointment> getAppointmentsByCustomerAndStatus(Long customerId, AppointmentStatus status);

//	List<Appointment> getConfirmedAppointments();
//
//	List<Appointment> getInProgressAppointments();
//
//	List<Appointment> getCancelledAppointments();

}
