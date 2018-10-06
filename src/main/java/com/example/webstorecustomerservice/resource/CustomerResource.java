package com.example.webstorecustomerservice.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.webstorecustomerservice.entity.Customer;
import com.example.webstorecustomerservice.exception.CustomerNotFoundException;
import com.example.webstorecustomerservice.service.CustomerService;

@RestController
@RequestMapping("customers")
public class CustomerResource {

	@Autowired
	private CustomerService customerService;

	@PutMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {

		customer = customerService.create(customer);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/customers/{customerId}")
				.buildAndExpand(customer.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("{id}")
	public Customer getCustomer(@PathVariable("id") Integer id) {
		Customer customer = customerService.getCustomer(id);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer does not exist");
		}
		return customer;
	}
	
	@DeleteMapping("{id}")
	public void deleteCustomer(@PathVariable("id") Integer id) {
		if (!customerService.delete(id)) {
			throw new CustomerNotFoundException("Customer does not exist");
		}
	}
	
}
