package com.nttd.mscustomer.service.impl;

import org.eclipse.microprofile.config.inject.ConfigProperty;

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
	
	@ConfigProperty(name  = "excepcion.003")
	String excepcion003;
	
	@ConfigProperty(name  = "excepcion.004")
	String excepcion004;
	
	@ConfigProperty(name  = "excepcion.005")
	String excepcion005;

	@ConfigProperty(name  = "error.generic")
	String errorgeneric;

	@ConfigProperty(name  = "state.valor.activo")
	String stateActivo;

	@ConfigProperty(name  = "state.valor.inactivo")
	String stateInactivo;

	

	@Transactional
	public ResponseDto saveCustomer(CustomerDto customerDto) {
		try {
			Customer customer = new Customer();
			customer.setTypeCustomer(customerDto.getTypeCustomer());
			customer.setNumberDocument(customerDto.getNumberDocument());
			customer.setName(customerDto.getName());
			customer.setLastname(customerDto.getLastname());
			customer.setState(stateActivo);
			customerRepository.persist(customer);
			return new ResponseDto(201, excepcion003,customer);
		} catch (Exception ex) {
			return new ResponseDto(400, errorgeneric, ex.getMessage());
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
		return new ResponseDto(201, excepcion004,customer);
		} catch (Exception ex) {
			return new ResponseDto(400, errorgeneric, ex.getMessage());
		}
	}

	public Customer getById(Long id) {
		return customerRepository.findById(id);
	}

	@Transactional
	public ResponseDto deleteCustomer(Long id) {
		try {

			Customer customer = this.getById(id);
			customer.setState(stateInactivo);
			customerRepository.persist(customer);

			return new ResponseDto(201, excepcion005,"	");

		} catch (Exception ex) {
			return new ResponseDto(400, errorgeneric, ex.getMessage());
		}
	}

}
