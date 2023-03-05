package com.app.dto;

import com.app.pojos.AppointmentStatus;
import com.app.pojos.Customer;
import com.app.pojos.Garage;
import com.app.pojos.ServiceType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointmentdto {
	private ServiceType type;
//	private boolean onSpotMechanic;
//	private Set<Services> services = new HashSet<>();
//	private PackageAppointment packageAppointment;

}
