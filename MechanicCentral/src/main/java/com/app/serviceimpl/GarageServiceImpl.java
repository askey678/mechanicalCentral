package com.app.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.Garagedto;
import com.app.exceptions.ResourceNotFoundException;
import com.app.pojos.Garage;
import com.app.repository.GarageRepo;
import com.app.service.GarageService;

@Service
@Transactional
public class GarageServiceImpl implements GarageService {

	@Autowired
	private GarageRepo garagerepo;

	@Autowired
	private ModelMapper modelmapper;

	@Override
	public List<Garagedto> getAllGarage() {

		List<Garage> garages = garagerepo.findAll();
		List<Garagedto> gargsdto = garages.stream().map(garage -> GarageTodto(garage)).collect(Collectors.toList());
		return gargsdto;

	}

	@Override
	public Garagedto getGarageById(Long id) {
		Garage garg = garagerepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Garage", "id", id));

		return GarageTodto(garg);
	}

	@Override
	public Garagedto addGarage(Garagedto gargdto) {
		Garage garg = dtoToGarage(gargdto);
		Garage savedGarage = garagerepo.save(garg);
		return GarageTodto(savedGarage);
	}

	@Override
	public Garagedto updateGarage(Garagedto gargdto, Long gargId) {
		Garage garg = garagerepo.findById(gargId)
				.orElseThrow(() -> new ResourceNotFoundException("Garage", "id", gargId));

		garg.setName(gargdto.getName());
		garg.setEmail(gargdto.getEmail());
		garg.setMobile(gargdto.getMobile());
		garg.setPassword(garg.getPassword());
		garg.setAddress(gargdto.getAddress());
		Garage updatedgarg = garagerepo.save(garg);
		return GarageTodto(updatedgarg);
	}

	@Override
	public void deleteGarage(Long id) {
		Garage garg = garagerepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Garage", "id", id));
		garagerepo.delete(garg);

	}

	private Garage dtoToGarage(Garagedto gargdto) {
		Garage garg = modelmapper.map(gargdto, Garage.class);
		return garg;
	}

	private Garagedto GarageTodto(Garage garg) {
		Garagedto gargdto = modelmapper.map(garg, Garagedto.class);
		return gargdto;
	}

}
