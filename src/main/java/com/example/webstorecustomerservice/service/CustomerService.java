package com.example.webstorecustomerservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.webstorecustomerservice.dao.CustomerDAO;
import com.example.webstorecustomerservice.entity.Customer;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private CartService cartService;

	public Customer create(Customer customer) {
		customer = customerDAO.save(customer);
		
		cartService.createCustomerCart(customer);
		
		return customer;
	}

	public boolean delete(Integer id) {
		try {
			customerDAO.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}

	public Customer getCustomer(Integer id) {
		Optional<Customer> opt = customerDAO.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		return null;
	}
}
