package fr.letitzen.demo.job;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.letitzen.demo.domain.Booking;
import fr.letitzen.demo.domain.BookingItem;
import fr.letitzen.demo.service.BookingService;

@Component
public class BootstrapJob {
	
	@Inject
	BookingService bookingService;
	
	@Transactional
	@PostConstruct
	public void injectData() {
		Calendar cal = new GregorianCalendar(2000, 0, 1);
		for (int i=0; i<25; i++) {
			Booking booking = new Booking();
			booking.setCustomerEmail("customer" + i + "@mail.com");
			booking.setBookingDate(cal.getTime());
			for(int j=0;j <3;j++) {
				BookingItem item = new BookingItem();
				item.setItemLabel("Item " + i);
				item.setItemUnitPrice(new BigDecimal(25.32));
				item.setBooking(booking);
				item.setQuantity(j + 1);
				booking.getItems().add(item);
			}
			bookingService.save(booking);
			
			cal.add(Calendar.DAY_OF_YEAR, 1);
		}
	}

}
