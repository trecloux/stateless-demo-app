package fr.letitzen.demo.web;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.SQLException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={"/test-env-context.xml", "/root-context.xml", "/servlet-context.xml"},
		loader=TestGenericWebXmlContextLoader.class)
@Transactional		
public class BookingControllerTest {
	
	@SuppressWarnings("SpringJavaAutowiringInspection")
    @Inject
	WebApplicationContext wac;
	@Inject
	DataSource dataSource;	
	
	
	private MockMvc mockMvc;

	@Test
	public void shouldRedirectBookingsToPage() throws Exception {
		mockMvc.perform(get("/bookings"))
			.andExpect(redirectedUrl("/bookings/1"));
	}

	@Test
	public void shouldPageBookings() throws Exception {
		mockMvc.perform(get("/bookings/1"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("bookings", "pager"))
			.andExpect(view().name("booking/list"));
	}
	
	@Test
	public void shouldPageBookingsWithMessage() throws Exception {
		mockMvc.perform(get("/bookings/1"))
            .andDo(print())
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("bookings", "pager"))
			.andExpect(view().name("booking/list"));
	}	
	
	@Test
	public void shouldViewABooking() throws Exception {
		mockMvc.perform(get("/booking/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("booking/view"));
	}	
	
	@Test
	public void shouldSaveABooking() throws Exception {
		mockMvc.perform(post("/booking").param("id", "1"))
			.andExpect(redirectedUrl("/bookings/1"))
            .andExpect(flash().attribute("info", "saved"));
	}
	
	@Test
	public void shouldDeleteABooking() throws Exception {
		mockMvc.perform(delete("/booking").param("id", "1"))
			.andExpect(redirectedUrl("/bookings/1"))
            .andExpect(flash().attribute("info","deleted"));
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
	
	@Before
	public void mockMvc() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
}
class TestGenericWebXmlContextLoader extends GenericWebXmlContextLoader {
	
	public TestGenericWebXmlContextLoader() {
		super("src/main/webapp", false);
	}
	
}	
