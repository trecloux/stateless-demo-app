package fr.letitzen.demo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Booking extends AbstractPersistable<Long> {
	@NotNull
	private Date bookingDate;
	@NotEmpty
	@Email
	private String customerEmail;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="booking", orphanRemoval=true)
	@Valid
	private List<BookingItem> items= new ArrayList<BookingItem>();

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public List<BookingItem> getItems() {
		return items;
	}

	public void setItems(List<BookingItem> items) {
		this.items = items;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

}
