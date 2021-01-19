package br.com.alelo.frota.service;

import br.com.alelo.frota.exceptions.VehicleNotFoundException;
import br.com.alelo.frota.model.Vehicle;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface VehicleService {

    Vehicle save(Vehicle vehicle);
    void deleteById(Long id);
    Page<Vehicle> findAll(int page, int limit, Boolean filter);
    Optional<Vehicle> findByPlate(String plate);
    Vehicle findById(Long id) throws VehicleNotFoundException;

}
