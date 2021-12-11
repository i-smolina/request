package ru.smolina.request.exceptions;

public class RequestNotFoundException extends RuntimeException{
	public RequestNotFoundException(String message) {
		super(message);
	}
}
