package com.huzaifabinzahoor.springdemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huzaifabinzahoor.springdemo.entity.Customer;
import com.huzaifabinzahoor.springdemo.exception.CustomerNotFoundException;
import com.huzaifabinzahoor.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	private List<Customer> theCustomerList;

	// adding dependency of customerService
	@Autowired
	private CustomerService customerService;

	// retrieving all customers from DB
	@GetMapping("/customers")
	public List<Customer> listCustomers() {
		return customerService.getCustomers();
	}

	// retrieving one specific customers requested from client url
	@GetMapping("/customers/{customerId}")
	public Customer listCustomers(@PathVariable int customerId) {

		Customer theCustomer = customerService.getCustomer(customerId);

		// check if the customer with the id is available or not
		if (theCustomer == null) {
			throw new CustomerNotFoundException("Customer Id not found exception" + customerId);
		}
		return theCustomer;
	}

	// this endpoint will handle the insertion of new customer to the DB
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theNewCustomer) {
		theNewCustomer.setId(0);
		customerService.saveCustomer(theNewCustomer);
		return theNewCustomer;
	}

	// this endpoint will handle the updation customer to the DB
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theNewCustomer) {
		customerService.saveCustomer(theNewCustomer);
		return theNewCustomer;
	}

	// this endpoint will handle the deletion of customer from the DB
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {

		Customer theCustomer = customerService.getCustomer(customerId);
		// check if the customer with the id is available or not
		if (theCustomer == null) {
			throw new CustomerNotFoundException("Customer Id not found exception" + customerId);
		}
		customerService.deleteCustomer(customerId);
		return "Customer Deleted Successfully !! ";

	}
}
