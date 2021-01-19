package br.com.alelo.frota;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@Log4j2
@SpringBootApplication
public class FrotaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrotaApplication.class, args);
		log.info("Vehicle API started successfully at {}", LocalDateTime.now());
	}

}
