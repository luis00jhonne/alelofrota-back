package br.com.alelo.frota.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;
import org.modelmapper.ModelMapper;

import br.com.alelo.frota.dto.model.VehicleDTO;

@Data
@Entity(name="vehicle")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 5004597882469304586L;
	
	private @Id @GeneratedValue Long id;
    private @Column(unique = true) String plate;
    private String model;
    private String manufacturer;
    private String color;
    private Boolean status;

    public VehicleDTO convertEntityToDTO() {
		return new ModelMapper().map(this, VehicleDTO.class);
	}

}
