package com.example.webstorecustomerservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webstorecustomerservice.entity.Customer;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Integer>{

}
