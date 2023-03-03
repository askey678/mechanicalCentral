package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
public class PackageAppointment extends BaseEntity{
	@ManyToOne
	@JoinColumn(name = "package_id")
	private Packages package_id;
	@ManyToOne
	@JoinColumn(name = "appointment_id")
	private Appointment appointment_id;

}
