package com.app.serviceimpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.AppointmentRequestdto;
import com.app.dto.AppointmentResponsedto;
import com.app.exceptions.ResourceNotFoundException;
import com.app.pojos.Appointment;
import com.app.pojos.AppointmentRequest;
import com.app.pojos.AppointmentStatus;
import com.app.pojos.Customer;
import com.app.pojos.Garage;
import com.app.pojos.Packages;
import com.app.pojos.ServiceType;
import com.app.pojos.Services;
import com.app.repository.AppointmentRepo;
import com.app.repository.CustomerRepo;
import com.app.repository.GarageRepo;
import com.app.repository.PackageRepo;
import com.app.repository.ServiceRepo;
import com.app.service.AppointmentService;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepo appointmentrepo;

	@Autowired
	private CustomerRepo customerrepo;

	@Autowired
	private GarageRepo garagerepo;

	@Autowired
	private ModelMapper modelmapper;

	@Autowired
	private ServiceRepo servicerepo;
	@Autowired
	private PackageRepo packagerepo;

	@Override
	public AppointmentResponsedto bookAppointment(Long customerId, AppointmentRequestdto appointmentRequest) {

		Customer customer = customerrepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
		Appointment appointment = new Appointment();
		appointment.setCustomer(customer);
		appointment.setStatus(AppointmentStatus.PENDING);
		appointment.setType(appointmentRequest.getServicetype());
		// set date

		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = currentDate.format(formatter);
		appointment.setDate(formattedDate);
		// set time
		LocalTime currentTime = LocalTime.now();
		DateTimeFormatter formating = DateTimeFormatter.ofPattern("HH:mm:ss");
		String formattedTime = currentTime.format(formating);
		appointment.setTime(formattedTime);

		// Determine which service type the appointment request is for

		if (appointmentRequest.getServicetype() == ServiceType.SERVICES) {
			// Find the requested services by ID and add them to the appointment
			Set<Services> services = new HashSet<>();
			for (Long serviceId : appointmentRequest.getServiceIds()) {
				Services service = servicerepo.findById(serviceId)
						.orElseThrow(() -> new ResourceNotFoundException("Service", "id", serviceId));
				services.add(service);
			}
			appointment.setServices(services);
		} else if (appointmentRequest.getServicetype() == ServiceType.PACKAGE) {
			// Find the requested package by ID and add it to the appointment
			Packages packages = packagerepo.findById(appointmentRequest.getPackageId()).orElseThrow(
					() -> new ResourceNotFoundException("Package", "id", appointmentRequest.getPackageId()));
			appointment.setPackagee(packages);
		} else if (appointmentRequest.getServicetype() == ServiceType.ONSPOTMECHANIC) {

			appointment.setOnSpotMechanic(appointmentRequest.isOnSpotMechanic());
		}

//		 Find all garages and send the appointment request to each of them
		List<Garage> garages = garagerepo.findAll();
		for (Garage garage : garages) {
			garage.AddAppointmentsRequests(modelmapper.map(appointmentRequest, AppointmentRequest.class));
		}

//
//			if (response.isAccepted()) {
//				// If the garage accepted the appointment, assign it to the appointment and
//				// break the loop
//				appointment.setGarage(garage);
//				appointment.setStatus(AppointmentStatus.ACCEPTED);
//				break;
//			}

		// Save the appointment to the database
		appointmentrepo.save(appointment);
		return modelmapper.map(appointment, AppointmentResponsedto.class);
	}

	@Override
	public List<Appointment> getAllAppointments() {
		List<Appointment> appointments = appointmentrepo.findAll();
		return appointments;
	}

//	@Override
//	public List<Appointment> getCancelledAppointments() {
//		return appointmentrepo.findBystatusCANCELLED();
//
//	}

	@Override
	public List<Appointment> getAppointmentsByGarageAndStatus(Long garageId, AppointmentStatus status) {
		Garage garage = new Garage();
		garage.setId(garageId);
		List<Appointment> appointments = appointmentrepo.findByGarageAndStatus(garage, status);
		return appointments;
	}

	@Override
	public List<Appointment> getAppointmentsByCustomerAndStatus(Long customerId, AppointmentStatus status) {
		Customer customer = new Customer();
		customer.setId(customerId);
		List<Appointment> appointments = appointmentrepo.findByCustomerAndStatus(customer, status);
		return appointments;

	}

	@Override
	public Appointment getAppointmentById(Long appointmentId) {

		return appointmentrepo.findById(appointmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", appointmentId));

	}

	@Override
	public AppointmentResponsedto updateAppointment(AppointmentRequestdto appointmentdto, Long appointmentId) {
		Appointment appointment = appointmentrepo.findById(appointmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", appointmentId));

		// updating the appointment
		return modelmapper.map(appointment, AppointmentResponsedto.class);
	}

	@Override
	public void deleteAppointment(Long appointmentId) {
		Appointment appointment = appointmentrepo.findById(appointmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", appointmentId));

		appointmentrepo.delete(appointment);

	}

	@Override
	public List<Appointment> getAppointmentsByGarage(Long garageId) {
		Garage garage = new Garage();
		garage.setId(garageId);
		return appointmentrepo.findByGarage(garage);
	}

	@Override
	public List<Appointment> getAppointmentsByCustomer(Long customerId) {
		Customer customer = new Customer();
		customer.setId(customerId);
		return appointmentrepo.findByCustomer(customer);
	}

//	@Override
//	public List<Appointment> getConfirmedAppointments() {
//		return appointmentrepo.findBystatusCONFIRMED();
//	}
//
//	@Override
//	public List<Appointment> getInProgressAppointments() {
//
//		return appointmentrepo.findBystatusINPROGRESS();
//	}

}
