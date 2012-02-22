package fr.letitzen.demo.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.letitzen.demo.domain.Booking;
import fr.letitzen.demo.service.BookingService;
import fr.letitzen.demo.web.Pager;

@Repository
@Transactional
public class BookingServiceImpl implements BookingService {
	
	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> findAll(Pager pager) {
		return em.createQuery("FROM Booking ORDER BY bookingDate")
			.setFirstResult(pager.getFirstRow()).setMaxResults(pager.getPageSize())
			.getResultList();
	}

	@Override
	public Booking findById(Long id) {
		return em.find(Booking.class, id);	
	}

	@Override
	public Booking save(Booking booking) {
		return em.merge(booking);
		
	}

	@Override
	public void deleteById(Long id) {
		Booking booking = findById(id);
		em.remove(booking);
	}

	@Override
	public int countAll() {
		return ((Long) em.createQuery("SELECT COUNT(*) FROM Booking").getSingleResult()).intValue();
	}

}
