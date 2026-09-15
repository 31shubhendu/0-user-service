package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {

	private Long userId;
	
	@Size(min=2,max=30, message=("firstname should be bw 3 to 30"))
	private String firstName;
	
	@Size(min=1, message=("last name required"))
	private String lastName;
	@Email(message="Email id should be valid")
	private String email;
	
	@Pattern(regexp="^[0-9]{10}$", message="phone no must be in 10 digits")
	private String phone;
	
	@NotNull(message="credential is required")
	private CredentialDTO credentialDTO; 
}
