package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Service;

public interface ServiceRepo extends JpaRepository<Service, Long> {

}
