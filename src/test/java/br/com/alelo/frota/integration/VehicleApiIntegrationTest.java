package br.com.alelo.frota.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import br.com.alelo.frota.dto.model.VehicleDTO;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class VehicleApiIntegrationTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Order(1)
	public void testCreateVehicleWithId10() {

		VehicleDTO dto = new VehicleDTO(10L, "AAA-1234", "Jetta", "Volkswagen", "Blue", Boolean.TRUE);

		// Create a new HttpEntity
		final HttpEntity<VehicleDTO> entity = new HttpEntity<>(dto);

		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange("http://localhost:" + port + "/api-alelo/vehicle", HttpMethod.POST, entity, String.class);

		assertEquals(201, responseEntity.getStatusCodeValue());
	}

	@Test
	@Order(2)
	public void testCreateVehicleWithId20() {

		VehicleDTO dto = new VehicleDTO(20L, "BBB-1234", "Jetta", "Volkswagen", "Blue", Boolean.TRUE);

		// Create a new HttpEntity
		final HttpEntity<VehicleDTO> entity = new HttpEntity<>(dto);

		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange("http://localhost:" + port + "/api-alelo/vehicle", HttpMethod.POST, entity, String.class);

		assertEquals(201, responseEntity.getStatusCodeValue());
	}

	@Test
	@Order(3)
	public void testFindAllVehicles() throws ParseException {

		ResponseEntity<String> responseEntity = this.restTemplate.exchange(
				"http://localhost:" + port + "/api-alelo/vehicle?page=0&limit=10", HttpMethod.GET, null, String.class);

		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	@Order(4)
	public void testFindVehicleById() {

		ResponseEntity<String> responseEntity = this.restTemplate.exchange(
				"http://localhost:" + port + "/api-alelo/vehicle/20", HttpMethod.GET, null, String.class);

		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	@Order(5) 
	public void testFindVehicleByIdThatNotExists() {
	  
	  ResponseEntity<String> responseEntity = this.restTemplate
	  .exchange("http://localhost:" + port + "/api-alelo/vehicle/3",
	  HttpMethod.GET, null, String.class);
	  
	  //Transaction not found 
	  assertEquals(404, responseEntity.getStatusCodeValue()); 
	}
	
	@Test
	@Order(6)
	public void testUpdateVehicleWithId10() {

		VehicleDTO dto = new VehicleDTO(10L, "AAA-1234", "Gol", "Volkswagen", "Black", Boolean.TRUE);

		// Create a new HttpEntity
		final HttpEntity<VehicleDTO> entity = new HttpEntity<>(dto);

		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange("http://localhost:" + port + "/api-alelo/vehicle/10", HttpMethod.PUT, entity, String.class);

		assertEquals(200, responseEntity.getStatusCodeValue());
	}
	
	@Test
	@Order(7) 
	public void testDeleteVehicleById() {
	  
	  ResponseEntity<String> responseEntity = this.restTemplate.exchange(
			  "http://localhost:" + port + "/api-alelo/vehicle/20",
			  HttpMethod.DELETE, null, String.class);
	  
	  //Transaction not found 
	  assertEquals(404, responseEntity.getStatusCodeValue()); 
	}

	@Test
	@Order(8)
	public void testFindVehicleByPlateNumber() {

		ResponseEntity<String> responseEntity = this.restTemplate.exchange(
				"http://localhost:" + port + "/api-alelo/vehicle?filter=AAA-234", HttpMethod.GET, null, String.class);

		assertEquals(200, responseEntity.getStatusCodeValue());
	}

}
