package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pojos.Customer;
import com.app.repository.CustomerRepo;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepo custrepo;
	
	

	@Override
	public List<Customer> getallcustomer() {
		return custrepo.findAll();
	}

	@Override
	public Customer getCustomer(Long id) {
		return custrepo.findById(id).orElse(null);
	}

	@Override
	public Customer addCustomer(Customer newcust) {
		return custrepo.save(newcust);
	}
	
	

}
