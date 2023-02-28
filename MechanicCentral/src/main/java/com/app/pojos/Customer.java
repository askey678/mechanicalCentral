package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString(exclude = "password")
@Table(name = "Customer")
public class Customer extends BaseEntity{

	@Column(length = 50, name = "cust_name", nullable = false)
	private String name;
	@Column(length = 50, unique = true, name = "cust_email", nullable = false)
	private String email;
	@Column(length = 12, name = "cust_mob", nullable = false)
	private Long mobile;
//	@Email
	@Column(length = 50, name = "cust_pass", nullable = false)
	private String password;

	@OneToMany(mappedBy = "customer")
	private List<Appointment> appointments = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
}
