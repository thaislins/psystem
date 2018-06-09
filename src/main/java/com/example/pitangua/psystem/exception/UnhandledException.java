package com.example.pitangua.psystem.exception;

public class UnhandledException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnhandledException(String message, Throwable cause) {
		super("Unhandled Exception: " + message, cause);
	}
}
