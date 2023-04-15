package br.com.vr.miniautorizador.domain.exception;

public class NotFoundException extends RuntimeException {

	public NotFoundException(String message) {
		super(message);
	}
}
