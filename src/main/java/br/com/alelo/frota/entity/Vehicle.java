package br.com.alelo.frota.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity(name="vehicle")
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    private @Id @GeneratedValue Long id;
    private @NotNull @Column(unique = true) String plate;
    private @NotNull String model;
    private @NotNull String manufacturer;
    private @NotNull String color;
    private Boolean status;


}
