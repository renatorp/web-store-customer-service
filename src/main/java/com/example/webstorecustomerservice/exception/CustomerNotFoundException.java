package com.example.webstorecustomerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2188536561254722942L;

	public CustomerNotFoundException(String message) {
		super(message);
	}
}
