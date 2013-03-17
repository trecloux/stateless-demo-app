package fr.letitzen.demo.web;

import fr.letitzen.demo.domain.Booking;
import fr.letitzen.demo.domain.BookingItem;
import fr.letitzen.demo.service.BookingService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@Controller
public class BookingController {
	
	@Inject
	BookingService bookingService;
	
	@RequestMapping(value="/bookings", method=RequestMethod.GET)
	public String list()  {
		return "redirect:/bookings/1";
	}
	
	@RequestMapping(value="/bookings/{page}", method=RequestMethod.GET)
	public String list(Model model, @PathVariable Integer page)  {
		Pager pager = new Pager(page, bookingService.countAll());
		List<Booking> bookings = bookingService.findAll(pager);
		model.addAttribute("bookings", bookings);
		model.addAttribute("pager", pager);
		return "booking/list";
	}
	
	@RequestMapping(value="/booking/new", method=RequestMethod.GET)
	public String create(Model model)  {
		model.addAttribute("booking", new Booking());
		return "booking/view";
	}
	
	@RequestMapping(value="/booking/{id}", method=RequestMethod.GET)
	public String get(Model model, @PathVariable Long id)  {
		Booking booking = bookingService.findById(id);
		if (booking == null) {
			throw new ResourceNotFoundException();
		}
		model.addAttribute("booking", booking);
		return "booking/view";
	}

	
	@RequestMapping(value="/booking", method=RequestMethod.POST)
	public String save(Model model, @Valid Booking cleanBbooking, Errors errors, RedirectAttributes redirectAttributes)  {
		if (errors.hasErrors()) {
			model.addAttribute("booking", cleanBbooking);
			return "booking/view";
		}
		for (BookingItem item : cleanBbooking.getItems()) {
			item.setBooking(cleanBbooking);
		}
		bookingService.save(cleanBbooking);
        redirectAttributes.addFlashAttribute("info", "saved");
		return "redirect:/bookings/1";
	}
	
	@RequestMapping(value="/booking", method=RequestMethod.DELETE)
	public String delete(Model model, @RequestParam Long id, RedirectAttributes redirectAttributes)  {
		try {
			bookingService.deleteById(id);
		} catch (DataIntegrityViolationException e) {
		}
		redirectAttributes.addFlashAttribute("info", "deleted");
		return "redirect:/bookings/1";
	}	
	
	@ModelAttribute("booking")
	public Booking loadEntity(Long id) {
		if (id != null) {
			return bookingService.findById(id);
		} else {
			return new Booking();
		}
	}
	
	@ModelAttribute("cleanBooking")
	public Booking loadAndCleanEntity(Long id) {
		if (id != null) {
			Booking booking = bookingService.findById(id);
			booking.getItems().clear();
			return booking;
		} else {
			return new Booking();
		}
	}	
	
}
