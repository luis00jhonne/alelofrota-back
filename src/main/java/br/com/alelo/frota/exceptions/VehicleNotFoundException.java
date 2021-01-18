package br.com.alelo.frota.exceptions;

public class VehicleNotFoundException extends RuntimeException {

    public VehicleNotFoundException(){
        super("O veículo informado não foi localizado");
    }
}
