package com.example.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Credential {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long credentilId;
	
	@Column(unique = true)
	private Long username;
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name="role")
	private RoleBaseAuthority roleBaseAuthority;
	
	@OneToOne
	@JoinColumn(name="user_id",unique=true)
	private User user;
	
	
	
	
	
	
}
