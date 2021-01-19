package br.com.alelo.frota.exceptions;

public class NotUniquePlateException extends Exception{
	
	private static final long serialVersionUID = 6208890125157318839L;
	
	public NotUniquePlateException(String msg){
		super(msg);
	}

}
