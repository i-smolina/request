package ru.smolina.request.exceptions;

public class RequestNotFoundException extends Exception{
	public RequestNotFoundException(String message) {
		super(message);
	}
}
