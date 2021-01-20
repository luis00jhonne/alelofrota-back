package br.com.alelo.frota.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.alelo.frota.model.Vehicle;
import br.com.alelo.frota.dto.model.VehicleDTO;
import br.com.alelo.frota.dto.response.Response;
import br.com.alelo.frota.exceptions.NotUniquePlateException;
import br.com.alelo.frota.exceptions.VehicleInvalidUpdateException;
import br.com.alelo.frota.exceptions.VehicleNotFoundException;
import br.com.alelo.frota.service.VehicleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/api-alelo/vehicle")
public class VehicleController {
    
	private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @ApiOperation(value = "Lista de veículos")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de veículos"),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @GetMapping
    public ResponseEntity<Response<List<VehicleDTO>>> listAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit) throws VehicleNotFoundException {
    	
    	Response<List<VehicleDTO>> response = new Response<>();
    	
    	Page<Vehicle> listVehicles = vehicleService.findAll(page, limit, filter);
		
		List<VehicleDTO> itemsDTO = new ArrayList<>();
		if (listVehicles.hasContent()) {
			listVehicles.stream().forEach(t -> itemsDTO.add(t.convertEntityToDTO()));
			itemsDTO.stream().forEach(dto -> {
				try {
					createSelfLinkInCollections(dto);
				} catch (VehicleNotFoundException e) {
					log.error("There are no vehicles registered");
				}
			});
		}
		
		response.setData(itemsDTO);
		response.setPage(listVehicles.getPageable());
		response.setTotalPages(listVehicles.getTotalPages());
		response.setTotalElements(listVehicles.getTotalElements());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Busca um veículo específico pelo Id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o veículo especificado pelo id"),
            @ApiResponse(code = 404, message = "Veículo não encontrado."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @GetMapping(path = "/{id}")
    public ResponseEntity<Response<VehicleDTO>> findById(@PathVariable Long id) throws VehicleNotFoundException {
    	
    	Response<VehicleDTO> response = new Response<>();
    	Vehicle vehicle = vehicleService.findById(id);
    	
    	VehicleDTO dto = vehicle.convertEntityToDTO();
    	
    	createSelfLink(vehicle, dto);
    	response.setData(dto);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @ApiOperation(value = "Cria um novo veículo")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Veículo cadastrado com sucesso"),
    		 @ApiResponse(code = 400, message = "Requisição incorreta."),
    		@ApiResponse(code = 204, message = "Veículo não cadastrado"),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @PostMapping
    public ResponseEntity<Response<VehicleDTO>> createVehicle(@Valid @RequestBody VehicleDTO dto,  BindingResult result) throws URISyntaxException, NotUniquePlateException {

    	Response<VehicleDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Optional<Vehicle> vehicleToFind = vehicleService.findByPlate(dto.getPlate());
		
		if(vehicleToFind.isPresent()) {
			throw new NotUniquePlateException("The plate already exists!");
		}
		
		Vehicle vehicleToCreate = vehicleService.save(dto.convertDTOToEntity());

		VehicleDTO dtoSaved = vehicleToCreate.convertEntityToDTO();
		createSelfLink(vehicleToCreate, dtoSaved);

		response.setData(dtoSaved);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Rota para atualizar um veículo na API")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Veículo atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição incorreta."),
            @ApiResponse(code = 404, message = "Veículo não encontrado."),
            @ApiResponse(code = 409, message = "Tentativa de atualização inválida."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @PutMapping(path="/{id}")
    public ResponseEntity<Response<VehicleDTO>> updateVehicle(@PathVariable("id") Long id, @Valid @RequestBody VehicleDTO dto, BindingResult result) throws VehicleNotFoundException, VehicleInvalidUpdateException{

    	Response<VehicleDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Vehicle vehicleToFind = vehicleService.findById(id);
		if (!vehicleToFind.getId().equals(dto.getId()) ) {
			throw new VehicleInvalidUpdateException("You don't have permission to change the vehicle id=" + dto.getId());
		}
		
		if (!vehicleToFind.getPlate().equals(dto.getPlate()) ) {
			throw new VehicleInvalidUpdateException("You don't have permission to change the plate=" + dto.getPlate());
		}

		Vehicle vehicleToUpdate = vehicleService.save(dto.convertDTOToEntity());
		
		VehicleDTO itemDTO = vehicleToUpdate.convertEntityToDTO();
		createSelfLink(vehicleToUpdate, itemDTO);
		response.setData(itemDTO);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Rota para deletar um veículo na API")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "Veículo deletado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição incorreta."),
            @ApiResponse(code = 404, message = "Veículo não encontrado."),
            @ApiResponse(code = 500, message = "Houve uma exceção no sistema."), })
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Response<String>> deleteVehicle(@PathVariable("id") Long id) throws VehicleNotFoundException{

    	Response<String> response = new Response<>();
		Vehicle vehicle = vehicleService.findById(id);
		
		vehicleService.deleteById(vehicle.getId());
		response.setData("Vehicle id=" + vehicle.getId() + " successfully deleted");
		
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
    
    private void createSelfLink(Vehicle vehicle, VehicleDTO vehicleDTO) {
		Link selfLink = WebMvcLinkBuilder.linkTo(VehicleController.class).slash(vehicle.getId()).withSelfRel();
		vehicleDTO.add(selfLink);
	}
	
	private void createSelfLinkInCollections(final VehicleDTO vehicleDTO) 
			throws VehicleNotFoundException {
		Link selfLink = linkTo(methodOn(VehicleController.class).findById(vehicleDTO.getId()))
				.withSelfRel().expand();
		vehicleDTO.add(selfLink);
	
	}
}
