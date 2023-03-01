package com.app.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CustomerDTO;
import com.app.pojos.Customer;
import com.app.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService custServ;
	@Autowired
	private ModelMapper mapper;

	public CustomerController() {
		super();
	}

	@PostMapping
	private Customer addcustomer(@RequestBody Customer newCust) {
		return custServ.addCustomer(newCust);
	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
//		Customer customer = custServ.getCustomer(id);
//		if (customer == null) {
//			return ResponseEntity.notFound().build();
//		}
//		CustomerDTO customerDTO = convertToDTO(customer);
//		return ResponseEntity.ok(customerDTO);
//	}

//	@PostMapping
//	public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO customerDTO) {
//		Customer customer = convertToEntity(customerDTO);
//		Customer savedCustomer = custServ.addCustomer(customer);
//		CustomerDTO savedCustomerDTO = convertToDTO(savedCustomer);
//		return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomerDTO);
//	}

}
