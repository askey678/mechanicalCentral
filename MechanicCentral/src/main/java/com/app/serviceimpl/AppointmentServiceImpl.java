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
import com.app.repository.AppointmentRequestrepo;
import com.app.repository.CustomerRepo;
import com.app.repository.GarageRepo;
import com.app.repository.PackageRepo;
import com.app.repository.ServiceRepo;
import com.app.service.AppointmentService;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRequestrepo appointmentreqrepo;

//	@Autowired
//	private AppointmentRepo appointmentrepo;
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
	public AppointmentResponsedto bookAppointment(Long customerId, AppointmentRequestdto appointmentreqdto) {

		Customer customer = customerrepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
		List<Garage> garages = garagerepo.findAll();
		
		for (Garage garage : garages) {
		    AppointmentRequest appointmentreq = new AppointmentRequest();
		    appointmentreq.setCustomer(customer);
		    appointmentreq.setStatus(AppointmentStatus.PENDING);
		    appointmentreq.setType(appointmentreqdto.getServicetype());
		    // set date
		    LocalDate currentDate = LocalDate.now();
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    String formattedDate = currentDate.format(formatter);
		    appointmentreq.setDate(formattedDate);
		    // set time
		    LocalTime currentTime = LocalTime.now();
		    DateTimeFormatter formating = DateTimeFormatter.ofPattern("HH:mm:ss");
		    String formattedTime = currentTime.format(formating);
		    appointmentreq.setTime(formattedTime);

		    // Determine which service type the appointmentreq request is for

		    if (appointmentreqdto.getServicetype() == ServiceType.SERVICES) {
		        // Find the requested services by ID and add them to the appointmentreq
		        Set<Services> services = new HashSet<>();
		        for (Long serviceId : appointmentreqdto.getServiceIds()) {
		            Services service = servicerepo.findById(serviceId)
		                    .orElseThrow(() -> new ResourceNotFoundException("Service", "id", serviceId));
		            services.add(service);

		        }
		        appointmentreq.setServices(services);
		    } else if (appointmentreqdto.getServicetype() == ServiceType.PACKAGE) {
		        // Find the requested package by ID and add it to the appointmentreq
		        Packages packages = packagerepo.findById(appointmentreqdto.getPackageId()).orElseThrow(
		                () -> new ResourceNotFoundException("Package", "id", appointmentreqdto.getPackageId()));
		        appointmentreq.setPackagee(packages);
		    } else if (appointmentreqdto.getServicetype() == ServiceType.ONSPOTMECHANIC) {
		        appointmentreq.setOnSpotMechanic(appointmentreqdto.isOnSpotMechanic());
		    }
		    appointmentreq.setGarage(garage);
		    // set other properties of appointmentRequest
		    garage.getAppointmentRequests().add(appointmentreq);
		    garagerepo.save(garage);
//		    appointmentreqrepo.save(appointmentreq);
		}
//		AppointmentRequest appointmentreq = new AppointmentRequest();
//		appointmentreq.setCustomer(customer);
//		appointmentreq.setStatus(AppointmentStatus.PENDING);
//		appointmentreq.setType(appointmentreqdto.getServicetype());
//		// set date
//
//		LocalDate currentDate = LocalDate.now();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		String formattedDate = currentDate.format(formatter);
//		appointmentreq.setDate(formattedDate);
//		// set time
//		LocalTime currentTime = LocalTime.now();
//		DateTimeFormatter formating = DateTimeFormatter.ofPattern("HH:mm:ss");
//		String formattedTime = currentTime.format(formating);
//		appointmentreq.setTime(formattedTime);
//
//		// Determine which service type the appointmentreq request is for
//
//		if (appointmentreqdto.getServicetype() == ServiceType.SERVICES) {
//			// Find the requested services by ID and add them to the appointmentreq
//			Set<Services> services = new HashSet<>();
//			for (Long serviceId : appointmentreqdto.getServiceIds()) {
//				Services service = servicerepo.findById(serviceId)
//						.orElseThrow(() -> new ResourceNotFoundException("Service", "id", serviceId));
//				services.add(service);
//
//			}
//			appointmentreq.setServices(services);
//		} else if (appointmentreqdto.getServicetype() == ServiceType.PACKAGE) {
//			// Find the requested package by ID and add it to the appointmentreq
//			Packages packages = packagerepo.findById(appointmentreqdto.getPackageId()).orElseThrow(
//					() -> new ResourceNotFoundException("Package", "id", appointmentreqdto.getPackageId()));
//			appointmentreq.setPackagee(packages);
//		} else if (appointmentreqdto.getServicetype() == ServiceType.ONSPOTMECHANIC) {
//
//			appointmentreq.setOnSpotMechanic(appointmentreqdto.isOnSpotMechanic());
//		}
//		// Save the appointmentreq to the database
//		appointmentreqrepo.save(appointmentreq);
//
////		 Find all garages and send the appointmentreq request to each of them
//		List<Garage> garages = garagerepo.findAll();
//		for (Garage garage : garages) {
//
//			appointmentreq.setGarage(garage);
//			// set other properties of appointmentRequest
//			garage.getAppointmentRequests().add(appointmentreq);
//			System.out.println(garage);
//			garagerepo.save(garage);
//			appointmentreqrepo.save(appointmentreq);
//		}

//		return modelmapper.map(appointmentreq, AppointmentResponsedto.class);
		return null;
	}

	@Override
	public List<Appointment> getAllAppointments() {
		return null;
	}

	@Override
	public Appointment getAppointmentById(Long appointmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppointmentResponsedto updateAppointment(AppointmentRequestdto appointmentdto, Long appointmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAppointment(Long appointmentId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Appointment> getAppointmentsByGarage(Long garageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> getAppointmentsByCustomer(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> getAppointmentsByGarageAndStatus(Long garageId, AppointmentStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> getAppointmentsByCustomerAndStatus(Long customerId, AppointmentStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<appointmentreq> getConfirmedappointmentreqs() {
//		return appointmentreqrepo.findBystatusCONFIRMED();
//	}
//
//	@Override
//	public List<appointmentreq> getInProgressappointmentreqs() {
//
//		return appointmentreqrepo.findBystatusINPROGRESS();
//	}

}
