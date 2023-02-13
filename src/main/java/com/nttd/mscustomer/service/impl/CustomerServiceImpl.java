package com.nttd.mscustomer.service.impl;

import com.nttd.mscustomer.dto.CustomerDto;
import com.nttd.mscustomer.dto.ResponseDto;
import com.nttd.mscustomer.entity.Customer;
import com.nttd.mscustomer.repository.CustomerRepository;
import com.nttd.mscustomer.service.CustomerService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CustomerServiceImpl implements CustomerService {

	@Inject
	private CustomerRepository customerRepository;

	@Transactional
	public ResponseDto saveCustomer(CustomerDto customerDto) {
		try {
			Customer customer = new Customer();
			customer.setTypeCustomer(customerDto.getTypeCustomer());
			customer.setNumberDocument(customerDto.getNumberDocument());
			customer.setName(customerDto.getName());
			customer.setLastname(customerDto.getLastname());
			customerRepository.persist(customer);
			return new ResponseDto(201, "Se registro correctamente.");
		} catch (Exception ex) {
			return new ResponseDto(400, "Bad Request.", ex.getMessage());
		}
	}

	@Transactional
	public ResponseDto updateCustomer(CustomerDto customerDto) {
		try {
		Customer customer = this.getById(customerDto.getIdCustomer());
		customer.setTypeCustomer(customerDto.getTypeCustomer());
		customer.setNumberDocument(customerDto.getNumberDocument());
		customer.setName(customerDto.getName());
		customer.setLastname(customerDto.getLastname());
		customerRepository.persist(customer);
		return new ResponseDto(201, "Se actualizo correctamente.");
		} catch (Exception ex) {
			return new ResponseDto(400, "Bad Request.", ex.getMessage());
		}
	}

	public Customer getById(Long id) {
		return customerRepository.findById(id);
	}

	@Transactional
	public ResponseDto deleteCustomer(Long id) {
		try {
		Customer customer = this.getById(id);
		customerRepository.delete(customer);
		return new ResponseDto(200, "Se elimino correctamente.");
		} catch (Exception ex) {
			return new ResponseDto(400, "Bad Request.", ex.getMessage());
		}
	}

}
