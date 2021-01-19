package br.com.alelo.frota.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
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
