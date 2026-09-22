package com.example.dto;

import com.example.models.RoleBaseAuthority;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CredentialDTO {
	private Long username;
//	private String password;
	private RoleBaseAuthority roleBaseAuthority;
}
