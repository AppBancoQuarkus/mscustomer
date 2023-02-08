package com.nttd.mscustomer.service;

import com.nttd.mscustomer.entity.Customer;
import com.nttd.mscustomer.repository.CustomerRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CustomerService {

	@Inject
	private CustomerRepository customerRepository;

	public void saveCustomer(Customer customer) {
		customerRepository.persist(customer);
	}
}
