package ru.smolina.request.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

public enum Role  implements GrantedAuthority{
	USER, ADMIN, OPERATOR;

	@Override
	public String getAuthority() {
		return name();
	}
	
	
}
