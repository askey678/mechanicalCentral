package com.app.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pojos.Customer;
import com.app.repository.CustomerRepo;
import com.app.service.CustomerService;

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
	public Customer addCustomer(Customer newcust) {
		return custrepo.save(newcust);
	}

	@Override
	public Customer getCustomerbyId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateUser(Customer cust, Long custId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		
	}
	
	

}
