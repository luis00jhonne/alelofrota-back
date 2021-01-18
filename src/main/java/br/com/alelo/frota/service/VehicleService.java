package br.com.alelo.frota.service;

import br.com.alelo.frota.entity.Vehicle;
import br.com.alelo.frota.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehicleService {

    private VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> findAll(){
        return vehicleRepository.findAll();
    }
}
