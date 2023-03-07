package com.app.service;

import java.util.List;

import com.app.dto.AppointmentRequestdto;
import com.app.dto.Garagedto;

public interface GarageService {

	List<Garagedto> getAllGarage();

	Garagedto getGarageById(Long id);

	Garagedto addGarage(Garagedto gargdto);

	Garagedto updateGarage(Garagedto gargdto, Long gargId);

	void deleteGarage(Long id);

	void AcceptAppointment(Long gargId, AppointmentRequestdto appointmentreq);

	void DeclineAppointment(Long gargId, AppointmentRequestdto appointmentreq);

}
