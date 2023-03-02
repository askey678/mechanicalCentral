package com.app.service;

import java.util.List;

import com.app.dto.Customerdto;

public interface CustomerService {
	List<Customerdto> getallcustomer();

	Customerdto getCustomerbyId(Long id);

	Customerdto addCustomer(Customerdto newcust);

	Customerdto updateUser(Customerdto cust, Long custId);

	void deleteUser(Long custId);

}
