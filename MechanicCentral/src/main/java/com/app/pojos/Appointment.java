package com.app.pojos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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

@Entity
public class Appointment extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "cust_id")
	private Customer customer;
	@ManyToOne
	@JoinColumn(name = "garage_id")
	private Garage garage;
	@Enumerated(EnumType.STRING)
	private ServiceType type;
	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;

	@ManyToMany(mappedBy = "appointments", cascade = { CascadeType.ALL })
    private Set<Service> services = new HashSet<Service>();
}
