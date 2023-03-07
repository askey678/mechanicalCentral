package com.app.dto;



import java.util.HashSet;
import java.util.Set;

import com.app.pojos.AppointmentStatus;
import com.app.pojos.ServiceType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AppointmentResponsedto {
	private Long id;
	private Long customerId;
	private Long garageId;
	private ServiceType serviceType;
	private AppointmentStatus status;
	private String date;
	private String time;
	private boolean onSpotMechanic;
	private Long packageId;
	private Set<Long> serviceIds = new HashSet<>();
	private Double totalPrice;
}

