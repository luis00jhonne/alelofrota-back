package br.com.alelo.frota.exceptions;

public class VehicleInvalidUpdateException extends Exception {
	
	private static final long serialVersionUID = -6443362632195638948L;
	
	public VehicleInvalidUpdateException(){
		super();
	}
	
	public VehicleInvalidUpdateException(String msg){
		super(msg);
	}

}
