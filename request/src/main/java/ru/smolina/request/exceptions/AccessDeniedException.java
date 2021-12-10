package ru.smolina.request.exceptions;

public class AccessDeniedException extends Exception{
	public AccessDeniedException(String message) {
		super(message);
	}
}
