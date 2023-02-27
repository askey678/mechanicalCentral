package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

}
