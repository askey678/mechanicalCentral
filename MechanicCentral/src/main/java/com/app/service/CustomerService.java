package com.app.service;

import java.util.List;

import com.app.pojos.Customer;

public interface CustomerService {
	List<Customer> getallcustomer();
	Customer getCustomerbyId(Long id);
	Customer addCustomer(Customer newcust);
	Customer updateUser(Customer cust, Long custId);
	void deleteUser(Integer userId);

}
