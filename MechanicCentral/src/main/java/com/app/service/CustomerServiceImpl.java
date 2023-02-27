package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.app.pojos.Customer;
import com.app.repository.CustomerRepo;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerRepo custrepo;

	@Override
	public List<Customer> getallcustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer AddCustomer(Customer newcust) {
		return custrepo.save(newcust);
	}
	
	

}
