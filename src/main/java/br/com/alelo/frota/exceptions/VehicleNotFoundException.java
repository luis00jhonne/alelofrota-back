package br.com.alelo.frota.exceptions;

public class VehicleNotFoundException extends Exception {

    private static final long serialVersionUID = -2586209354700102349L;

    public VehicleNotFoundException(){
        super();
    }

    public VehicleNotFoundException(String msg){
        super(msg);
    }

    public VehicleNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
