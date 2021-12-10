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
//@Entity
//@Table(name="role")
//public class Role implements GrantedAuthority{
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long role_id;
//	private String role;		
//	
//	public Long getRole_id() {
//		return role_id;
//	}
//	public void setRole_id(Long role_id) {
//		this.role_id = role_id;
//	}
//	public String getRole() {
//		return role;
//	}
//	public void setRole(String role) {
//		this.role = role;
//	}
//	@Override
//	public String getAuthority() {
//		return "USER";
//	}
//	
//	
//}
