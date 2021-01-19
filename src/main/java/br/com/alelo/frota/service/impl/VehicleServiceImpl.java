package br.com.alelo.frota.service.impl;

import br.com.alelo.frota.exceptions.VehicleNotFoundException;
import br.com.alelo.frota.model.Vehicle;
import br.com.alelo.frota.repository.VehicleRepository;
import br.com.alelo.frota.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    public Page<Vehicle> findAll(int page, int limit, Boolean filter){
        Pageable paging = PageRequest.of(page, limit);
        Page<Vehicle> pageResult;

        if (filter == null){
            pageResult = vehicleRepository.findAll(paging);
        } else {
            pageResult = vehicleRepository.findByStatus(filter, paging);
        }

        return pageResult;
    }

    public Vehicle findById(Long id) throws VehicleNotFoundException {
        return vehicleRepository.findById(
                id).orElseThrow(() -> new VehicleNotFoundException("Vehicle id=" + id + " not found"));
    }

    @Override
    public Optional<Vehicle> findByPlate(String plate){
        return vehicleRepository.findOneByPlate(plate);
    }

    public Vehicle save(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteById(Long id) {
        vehicleRepository.deleteById(id);
    }

}
