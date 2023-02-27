package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Customer;
import com.app.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService custServ;

	public CustomerController() {
		super();
	}
	
	@PostMapping
	private Customer addcustomer(@RequestBody Customer newCust) {
		return custServ.AddCustomer(newCust);
	}
	
	

}
