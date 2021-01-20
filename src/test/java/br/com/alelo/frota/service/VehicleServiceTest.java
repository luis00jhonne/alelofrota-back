package br.com.alelo.frota.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import br.com.alelo.frota.model.Vehicle;
import br.com.alelo.frota.repository.VehicleRepository;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
public class VehicleServiceTest {
	
	@Autowired
	private VehicleService service;

	@MockBean
	private VehicleRepository repository;
	
	@Test
	@Order(1)
	public void testSaveVehicle() {
		
		BDDMockito.given(repository.save(Mockito.any(Vehicle.class)))
			.willReturn(getMockTravel());
		Vehicle response = service.save(new Vehicle());
		
		assertNotNull(response);
		assertEquals("AAA-1234", response.getPlate());
	}
	
	@Test
	@Order(2)
	public void testFindByPlateNumber() {
		
		BDDMockito.given(repository.findOneByPlate(Mockito.anyString()))
			.willReturn(Optional.of(new Vehicle()));
		
		Optional<Vehicle> response = service.findByPlate("AAA-1234");
		assertTrue(!response.isEmpty());
	}
	
	@Test
	@Order(3)
	public void testFindAllVehicles() {
		
		List<Vehicle> transactions = new ArrayList<>();
		transactions.add(getMockTravel());
		Page<Vehicle> page = new PageImpl<>(transactions);
		
		BDDMockito.given(repository.findAll(Mockito.any(Pageable.class))).willReturn(page);
		
		Page<Vehicle> response = service.findAll(0, 10, null);
		assertNotNull(response);
	}
	
	private Vehicle getMockTravel() {
		
		Vehicle vehicle = new Vehicle(null, "AAA-1234", "Jetta", "Volkswagen", "Blue", Boolean.TRUE);
		return vehicle;
	}
	
	@AfterAll
	private void tearDown() {
		repository.deleteAll();
	}

}