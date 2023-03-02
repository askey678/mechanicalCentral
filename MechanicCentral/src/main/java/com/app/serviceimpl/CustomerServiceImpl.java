package com.app.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.Customerdto;
import com.app.exceptions.ResourceNotFoundException;
import com.app.pojos.Customer;
import com.app.repository.CustomerRepo;
import com.app.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo custrepo;

	@Override
	public List<Customerdto> getallcustomer() {
		List<Customer> customers = custrepo.findAll();
		List<Customerdto> custdto = customers.stream().map(customer -> customertodto(customer))
				.collect(Collectors.toList());
		return custdto;
	}

	@Override
	public Customerdto addCustomer(Customerdto custdto) {
		Customer cust = this.dtotocustomer(custdto);
		Customer savedCustomer = this.custrepo.save(cust);
		Customerdto customerdto = this.customertodto(savedCustomer);
		return customerdto;
	}

	@Override
	public Customerdto getCustomerbyId(Long id) {
		Customer cust = custrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
		return customertodto(cust);
	}

	@Override
	public Customerdto updateUser(Customerdto custdto, Long custId) {
		Customer cust = custrepo.findById(custId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "id", custId));

		cust.setName(custdto.getName());
		cust.setEmail(custdto.getEmail());
		cust.setMobile(custdto.getMobile());
		cust.setPassword(custdto.getPassword());

		Customer updatedcustomer = custrepo.save(cust);
		return customertodto(updatedcustomer);

	}

	@Override
	public void deleteUser(Long CustId) {
		Customer cust=custrepo.findById(CustId).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", CustId));
		custrepo.delete(cust);

	}

	private Customer dtotocustomer(Customerdto custdto) {
		Customer cust = new Customer();
		cust.setId(custdto.getId());
		cust.setName(custdto.getName());
		cust.setEmail(custdto.getEmail());
		cust.setMobile(custdto.getMobile());
		cust.setPassword(custdto.getPassword());
		return cust;

	}

	private Customerdto customertodto(Customer cust) {
		Customerdto custdto = new Customerdto();
		custdto.setId(cust.getId());
		custdto.setName(cust.getName());
		custdto.setEmail(cust.getEmail());
		custdto.setMobile(cust.getMobile());
		custdto.setPassword(cust.getPassword());
		return custdto;

	}

}
