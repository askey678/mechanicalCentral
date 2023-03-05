package com.app.serviceimpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.Appointmentdto;
import com.app.exceptions.ResourceNotFoundException;
import com.app.pojos.Appointment;
import com.app.pojos.AppointmentStatus;
import com.app.pojos.Customer;
import com.app.pojos.Garage;
import com.app.repository.AppointmentRepo;
import com.app.repository.CustomerRepo;
import com.app.repository.GarageRepo;
import com.app.service.AppointmentService;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepo appointmentRepo;

	@Autowired
	private CustomerRepo customerrepo;

	@Autowired
	private GarageRepo garagerepo;

	@Autowired
	private ModelMapper modelmapper;

	@Override
	public Appointment bookAppointment(Appointmentdto appointmentdto, Long customerId, Long garageId) {

		Customer customer = customerrepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));

		Garage garage = garagerepo.findById(garageId)
				.orElseThrow(() -> new ResourceNotFoundException("Garage", "id", garageId));

		Appointment appointment = modelmapper.map(appointmentdto, Appointment.class);
		appointment.setStatus(AppointmentStatus.valueOf("PENDING"));
		appointment.setCustomer(customer);
		appointment.setGarage(garage);
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

		// set AppointmentServicetype
		appointment.setType(appointmentdto.getType());

		// save
		Appointment appointed = appointmentRepo.save(appointment);

		return appointed;
	}

	@Override
	public List<Appointment> getAllAppointments() {
		List<Appointment> appointments = appointmentRepo.findAll();
		return appointments;
	}

	@Override
	public List<Appointment> getAppointmentsByGarageAndStatus(Long garageId, AppointmentStatus status) {
		Garage garage = new Garage();
		garage.setId(garageId);
		List<Appointment> appointments = appointmentRepo.findByGarageAndStatus(garage, status);
		return appointments;
	}

	@Override
	public List<Appointment> getAppointmentsByCustomerAndStatus(Long customerId, AppointmentStatus status) {
		Customer customer = new Customer();
		customer.setId(customerId);
		List<Appointment> appointments = appointmentRepo.findByCustomerAndStatus(customer, status);
		return appointments;

	}

	@Override
	public Appointment getAppointmentById(Long appointmentId) {

		return appointmentRepo.findById(appointmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", appointmentId));

	}

	@Override
	public Appointment updateAppointment(Appointmentdto appointmentdto, Long appointmentId) {
		Appointment appointment = appointmentRepo.findById(appointmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", appointmentId));

		// updating the appointment
		return appointment;
	}

	@Override
	public void deleteAppointment(Long appointmentId) {
		Appointment appointment = appointmentRepo.findById(appointmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", appointmentId));

		appointmentRepo.delete(appointment);

	}

	@Override
	public List<Appointment> getAppointmentsByGarage(Long garageId) {
		Garage garage = new Garage();
		garage.setId(garageId);
		return appointmentRepo.findByGarage(garage);
	}

	@Override
	public List<Appointment> getAppointmentsByCustomer(Long customerId) {
		Customer customer = new Customer();
		customer.setId(customerId);
		return appointmentRepo.findByCustomer(customer);
	}

	@Override
	public List<Appointment> getConfirmedAppointments() {
		return appointmentRepo.findBystatusCONFIRMED();
	}

	@Override
	public List<Appointment> getInProgressAppointments() {

		return appointmentRepo.findBystatusINPROGRESS();
	}

	@Override
	public List<Appointment> getCancelledAppointments() {
		return appointmentRepo.findBystatusCANCELLED();

	}

}
