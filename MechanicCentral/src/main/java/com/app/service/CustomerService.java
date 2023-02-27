package com.app.service;

import java.util.List;

import com.app.pojos.Customer;

public interface CustomerService {
	List<Customer> getallcustomer();
	Customer getCustomer();
	Customer AddCustomer(Customer newcust);

}
