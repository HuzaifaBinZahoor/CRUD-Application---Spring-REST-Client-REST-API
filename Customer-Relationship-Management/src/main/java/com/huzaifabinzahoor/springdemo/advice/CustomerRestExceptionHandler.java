package com.huzaifabinzahoor.springdemo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.huzaifabinzahoor.springdemo.entity.CustomertErrorResponce;
import com.huzaifabinzahoor.springdemo.exception.CustomerNotFoundException;

@ControllerAdvice
public class CustomerRestExceptionHandler {
	// add exception handling code here

	// Adding an exception handler @ExceptionHandler
	@ExceptionHandler
	public ResponseEntity<CustomertErrorResponce> handleException(CustomerNotFoundException exc) {

		// create a studentErrorResponce
		CustomertErrorResponce error = new CustomertErrorResponce(HttpStatus.NOT_FOUND.value(), exc.getMessage(),
				System.currentTimeMillis());

		// return the response entity

		return new ResponseEntity<CustomertErrorResponce>(error, HttpStatus.NOT_FOUND);

	}

	// add another exception handler to catch all any exception
	@ExceptionHandler
	public ResponseEntity<CustomertErrorResponce> handleException(Exception exc) {
		// create a studentErrorResponce
		CustomertErrorResponce error = new CustomertErrorResponce(HttpStatus.BAD_REQUEST.value(), exc.getMessage(),
				System.currentTimeMillis());

		// return the response entity
		return new ResponseEntity<CustomertErrorResponce>(error, HttpStatus.BAD_REQUEST);
	}

}
