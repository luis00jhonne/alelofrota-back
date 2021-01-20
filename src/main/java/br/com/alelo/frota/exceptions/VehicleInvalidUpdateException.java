package br.com.alelo.frota.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class VehicleInvalidUpdateException extends Exception {
	
	private static final long serialVersionUID = -6443362632195638948L;
	
	public VehicleInvalidUpdateException(){
		super();
	}
	
	public VehicleInvalidUpdateException(String msg){
		super(msg);
	}

}
