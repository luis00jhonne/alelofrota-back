package br.com.alelo.frota.service;

import br.com.alelo.frota.entity.Vehicle;
import br.com.alelo.frota.exceptions.VehicleNotFoundException;
import java.util.List;
import java.util.Optional;

public interface VehicleService {

    Vehicle save(Vehicle vehicle);
    void deleteById(Long id);
    List<Vehicle> findAll(int page, int limit, Boolean filter);
    Optional<Vehicle> findByPlate(String plate);
    Vehicle findById(Long id) throws VehicleNotFoundException;

}
