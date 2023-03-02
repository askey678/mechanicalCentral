package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.Customerdto;
import com.app.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService custServ;

	public CustomerController() {
		super();
	}

	@PostMapping("/")
	public ResponseEntity<Customerdto> createCustomer(@Valid @RequestBody Customerdto custdto) {
		Customerdto customerdto = custServ.addCustomer(custdto);
		return new ResponseEntity<Customerdto>(customerdto, HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<Customerdto>> getallCustomers() {
		return ResponseEntity.ok(custServ.getallcustomer());
	}

	@GetMapping("/{UserId}")
	public ResponseEntity<Customerdto> getCustomerbyId(@PathVariable Long UserId) {
		return ResponseEntity.ok(custServ.getCustomerbyId(UserId));
	}

	@PutMapping("/{CustId}")
	public ResponseEntity<Customerdto> updateCustomer(@Valid @RequestBody Customerdto custdto, @PathVariable Long CustId) {
		Customerdto updatedcust = custServ.updateUser(custdto, CustId);
		return ResponseEntity.ok(updatedcust);
	}

	@DeleteMapping("/{CustId}")
	public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable("CustId") Long cid) {
		custServ.deleteUser(cid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Customer deleted successfully", true), HttpStatus.OK);

	}

}
