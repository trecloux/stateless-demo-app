package fr.letitzen.demo.service;

import java.util.List;

import fr.letitzen.demo.domain.Booking;
import fr.letitzen.demo.web.Pager;


public interface BookingService {

	List<Booking> findAll(Pager pager);

	Booking findById(Long id);

	Booking save(Booking booking);

	void deleteById(Long id);

	int countAll();

}
