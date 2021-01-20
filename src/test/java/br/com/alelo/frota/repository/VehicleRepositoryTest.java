package br.com.alelo.frota.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import br.com.alelo.frota.model.Vehicle;


@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
public class VehicleRepositoryTest {
	
	@Autowired
	private VehicleRepository repository;
	
	static final String PLATE_NUMBER = "AAA-1234";
	
	@BeforeAll
	private void setUp() {
		
		Vehicle vehicle = new Vehicle(null, PLATE_NUMBER, "Jetta", "Volkswagen", "Blue", Boolean.TRUE);
		
		repository.save(vehicle);
	}
	
	@Test
	@Order(1)
	public void testSaveVehicle() {
		
		Vehicle vehicle = new Vehicle(null, PLATE_NUMBER, "Jetta", "Volkswagen", "Blue", Boolean.TRUE);
		
		Vehicle response = repository.save(vehicle);
		assertNotNull(response);
	}
	
	@Test
	@Order(2)
	public void testFindByPlateNumber(){
		
		Optional<Vehicle> response = repository.findOneByPlate(PLATE_NUMBER);
		assertFalse(response.isEmpty());
	}
	
	@AfterAll
	private void tearDown() {
		repository.deleteAll();
	}

}