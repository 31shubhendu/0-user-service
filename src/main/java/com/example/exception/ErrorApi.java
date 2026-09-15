package com.example.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorApi {

	private String message;
	private String status;
	private String error;
	private LocalDateTime localDateTime;
}
