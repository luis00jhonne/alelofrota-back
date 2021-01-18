package br.com.alelo.frota.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity(name="vehicle")
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    private @Id @GeneratedValue Long id;
    private @NotNull String plate;
    private @NotNull String model;
    private @NotNull String manufacturer;
    private @NotNull String color;
    private Boolean status;


}
