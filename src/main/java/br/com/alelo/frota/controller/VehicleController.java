package br.com.alelo.frota.controller;

import br.com.alelo.frota.entity.Vehicle;
import br.com.alelo.frota.exceptions.VehicleNotFoundException;
import br.com.alelo.frota.service.impl.VehicleServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@Validated
@RequestMapping("/vehicle")
public class VehicleController {
    private VehicleServiceImpl vehicleService;

    @Autowired
    public VehicleController(VehicleServiceImpl vehicleService){
        this.vehicleService = vehicleService;
    }

    @ApiOperation(value = "Lista veículos")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de veículos"),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @GetMapping
    public ResponseEntity<?> listAll(
            @RequestParam(required = false) Boolean filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit) {

        return ResponseEntity.ok(this.vehicleService.findAll(page, limit, filter));
    }

    @ApiOperation(value = "Buscar Veículo pela Placa")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de veículos"),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @GetMapping(path = "/filter")
    public ResponseEntity<?> listByPlate(
            @RequestParam(required = false) String filter) {

        return ResponseEntity.ok(this.vehicleService.findByPlate(filter));
    }


    @ApiOperation(value = "Busca um veículo específico pelo Id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o veículo especificado pelo id"),
            @ApiResponse(code = 404, message = "Veículo não encontrado."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @GetMapping(path = "/{id}")
    public ResponseEntity<Vehicle> listById(@PathVariable Long id) throws VehicleNotFoundException {

        return ResponseEntity.ok().body(this.vehicleService.findById(id));

    }

    @ApiOperation(value = "Cria um novo veículo")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Veículo cadastrado com sucesso"),
            @ApiResponse(code = 204, message = "Veículo não cadastrado"),
            @ApiResponse(code = 404, message = "Veículo não encontrado."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicle) throws URISyntaxException {

        vehicleService.save(vehicle);

        if (vehicle.getId() == null)
            ResponseEntity.noContent().build();

        return ResponseEntity.created(new URI("/vehicle/" + vehicle.getId())).build();
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id){

        Vehicle vehicle = vehicleService.findById(id);

        if (vehicle == null){
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.created(null).build();
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Vehicle> deleteVehicle(@Valid @PathVariable Long id){

        return ResponseEntity.created(null).build();
    }
}
