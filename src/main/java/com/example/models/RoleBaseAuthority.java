package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum RoleBaseAuthority {
	ROLE_USER("USER"),
	ROSE_ADMIN("ADMIN");
	
	private final String role;
	
}
