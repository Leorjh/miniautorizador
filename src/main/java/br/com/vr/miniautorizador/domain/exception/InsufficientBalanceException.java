package br.com.vr.miniautorizador.domain.exception;

public class InsufficientBalanceException extends RuntimeException {

	public InsufficientBalanceException(String message) {
		super(message);
	}
}
