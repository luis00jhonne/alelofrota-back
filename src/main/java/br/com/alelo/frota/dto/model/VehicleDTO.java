package br.com.alelo.frota.dto.model;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.alelo.frota.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleDTO extends RepresentationModel<VehicleDTO> {
	
	private Long id;
	
	@NotNull(message="The plate number cannot be null")
    private String plate;
	
	@NotNull(message="The model cannot be null")
    private String model;
	
	@NotNull(message="The manufacturer cannot be null")
    private String manufacturer;
    
	@NotNull(message="The color cannot be null")
    private String color;
	
	private Boolean status;

	public Vehicle convertDTOToEntity() {
		return new ModelMapper().map(this, Vehicle.class);
	}
}
