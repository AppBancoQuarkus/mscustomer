package com.nttd.mscustomer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "customer")
public class Customer {

	@Id
	@GeneratedValue
	private Long idCustomer;
	private String typeCustomer;
	private String numberDocument;
	private String name;
	private String lastname;
	private String state;

}
