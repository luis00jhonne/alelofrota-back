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

    public Page<Vehicle> findAll(int page, int limit, String filter){
        Pageable paging = PageRequest.of(page, limit);
        Page<Vehicle> pageResult;

        if (filter != null){
        	if (filter.toLowerCase().equals("true") || filter.toLowerCase().equals("false")) {
        		Boolean status = filter.toLowerCase().equals("true") ? Boolean.TRUE : Boolean.FALSE;
        		pageResult = vehicleRepository.findByStatus(status, paging);
        	} else {
        		String plate = String.join("-", filter.substring(0,  3), filter.substring(3));
            	pageResult = vehicleRepository.findByPlate(plate, PageRequest.of(0, 1));
        	}
       } else {
        	pageResult = vehicleRepository.findAll(paging);
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
