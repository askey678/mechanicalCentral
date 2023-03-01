package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
	@Column(length = 50, name = "cust_pass", nullable = false)
	private String password;

	@OneToMany(mappedBy = "customer")
	private List<Appointment> appointments = new ArrayList<>();


	
}
