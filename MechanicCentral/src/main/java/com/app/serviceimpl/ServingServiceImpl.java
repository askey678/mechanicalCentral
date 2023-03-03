package com.app.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.Servicedto;
import com.app.exceptions.ResourceNotFoundException;
import com.app.pojos.Services;
import com.app.repository.ServiceRepo;
import com.app.service.ServingService;

@Service
@Transactional
public class ServingServiceImpl implements ServingService {

	@Autowired
	private ModelMapper modelmapper;

	@Autowired
	private ServiceRepo servicerepo;

	@Override
	public List<Servicedto> getAllServices() {

		List<Services> services = servicerepo.findAll();

		List<Servicedto> servicedto = services.stream().map(service -> serviceTodto(service))
				.collect(Collectors.toList());

		return servicedto;
	}

	@Override
	public Servicedto getServiceById(Long serviceid) {
		Services service = servicerepo.findById(serviceid)
				.orElseThrow(() -> new ResourceNotFoundException("Service", "id", serviceid));
		return serviceTodto(service);
	}

	@Override
	public Servicedto addservice(Servicedto servicedto) {
		Services service = dtoToService(servicedto);
		Services savedservice = servicerepo.save(service);
		return serviceTodto(savedservice);
	}

	@Override
	public Servicedto updateService(Servicedto servicedto, Long id) {
		Services service = servicerepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Service", "id", id));
		service.setName(servicedto.getName());
		service.setDescription(servicedto.getDescription());
		service.setPrice(servicedto.getPrice());
		servicerepo.save(service);
		return serviceTodto(service);
	}

	@Override
	public void deleteService(Long id) {
		Services service = servicerepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Service", "id", id));
		servicerepo.delete(service);

	}

	private Services dtoToService(Servicedto servicedto) {
		Services service = modelmapper.map(servicedto, Services.class);
		return service;

	}

	private Servicedto serviceTodto(Services service) {
		Servicedto servicedto = modelmapper.map(service, Servicedto.class);
		return servicedto;
	}

}
