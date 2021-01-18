package br.com.alelo.frota.service;

import br.com.alelo.frota.entity.Vehicle;
import br.com.alelo.frota.exceptions.VehicleNotFoundException;
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

    public Vehicle findById(Long id){
        return vehicleRepository.findById(
                id).orElseThrow(() -> new VehicleNotFoundException());
    }

    public Vehicle findByPlate(String plate){
        //remover campos especiais
        return vehicleRepository.findOneByPlate(
                plate).orElseThrow(() -> new VehicleNotFoundException());
    }

    public List<Vehicle> findByStatus(Boolean status){
        return this.vehicleRepository.findByStatus(status);
    }
}
