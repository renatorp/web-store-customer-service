package com.example.webstorecustomerservice.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Customer {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	private String surname;
	
	private Date birthDate;
	
}
