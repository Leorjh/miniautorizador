package br.com.vr.miniautorizador.domain.exception;

public class IncorrectPasswordException extends RuntimeException {

	public IncorrectPasswordException(String message) {
		super(message);
	}
}
