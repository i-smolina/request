package ru.smolina.request.domain;

public enum Status {
	DRAFT, SENT, ACCEPTED, REJECTED;
	
	public String getAuthority() {
		return name();
	}
	
}
