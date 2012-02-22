package fr.letitzen.demo.service;


import static org.fest.assertions.Assertions.assertThat;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.ext.hsqldb.HsqldbConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.letitzen.demo.domain.Booking;
import fr.letitzen.demo.service.BookingService;
import fr.letitzen.demo.web.Pager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/test-env-context.xml", "/root-context.xml"})
@Transactional
public class BookingServiceTest {
	
	@Inject
	BookingService bookingService;	
	
	@Inject
	DataSource dataSource;
	
	
	@Test
	public void shouldFindAllBookings() throws Exception {
		List<Booking> bookings = bookingService.findAll(new Pager(1,100));
		assertThat(bookings).isNotEmpty();
		assertThat(bookings).hasSize(1);
	}
	
	@Test
	public void shouldCountBookings() throws Exception {
		assertThat(bookingService.countAll()).isEqualTo(1);
	}
	
	/* --------------------- */
	
	@Before
	public void injectData() throws DatabaseUnitException, SQLException {
		IDatabaseConnection conn = new HsqldbConnection(dataSource.getConnection(), null);
		IDataSet data = new XmlDataSet(getClass().getResourceAsStream("/test-data.xml"));
		try{
			DatabaseOperation.CLEAN_INSERT.execute(conn, data);
		}finally{
		  conn.close();
		}
	}
	
	
}
