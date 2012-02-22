package fr.letitzen.demo.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class BookingItem extends AbstractPersistable<Long> {
	
	@ManyToOne
	private Booking booking;
	
	@NotNull
	@NotEmpty
	private String itemLabel;
	@NotNull
	@Min(1)
	private Integer quantity;
	@NotNull
	@Min(0)
	private BigDecimal itemUnitPrice;
	public String getItemLabel() {
		return itemLabel;
	}
	public void setItemLabel(String itemLabel) {
		this.itemLabel = itemLabel;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getItemUnitPrice() {
		return itemUnitPrice;
	}
	public void setItemUnitPrice(BigDecimal itemUnitPrice) {
		this.itemUnitPrice = itemUnitPrice;
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}


}
