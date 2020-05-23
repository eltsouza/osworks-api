package com.algaworks.osworks.domain.exception;

public class NegocioException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	//Contrutor da Exception
	public NegocioException(String message) {
		super(message);
	}

}
