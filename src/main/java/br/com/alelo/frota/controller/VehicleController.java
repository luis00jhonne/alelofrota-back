package br.com.alelo.frota.controller;

import br.com.alelo.frota.entity.Vehicle;
import br.com.alelo.frota.exceptions.VehicleNotFoundException;
import br.com.alelo.frota.service.VehicleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/vehicle")
public class VehicleController {
    private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<Vehicle> listAll() {
        return this.vehicleService.findAll();
    }

    /*@GetMapping()
    public List<Vehicle> listByFilter(@RequestParam Object filter) {
        return this.vehicleService.findByStatus((Boolean) filter);
    }*/

    @ApiOperation(value = "Busca um veículo específico")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o veículo especificado pelo id"),
            @ApiResponse(code = 404, message = "Veículo não encontrado."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @GetMapping(path = "/{id}")
    public ResponseEntity<Vehicle> listById(@PathVariable Long id) {

        return ResponseEntity.ok().body(this.vehicleService.findById(id));

    }

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicle){

        return ResponseEntity.created(null).build();
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@Valid @PathVariable Long id){

        return ResponseEntity.created(null).build();
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Vehicle> deleteVehicle(@Valid @PathVariable Long id){

        return ResponseEntity.created(null).build();
    }
}
