package ru.smolina.request.exceptions;

public class InvalidRequestStatusException extends RuntimeException{
	public InvalidRequestStatusException(String message) {
		super(message);
	}

}
