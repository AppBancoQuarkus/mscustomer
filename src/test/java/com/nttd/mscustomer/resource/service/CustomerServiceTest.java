package com.nttd.mscustomer.resource.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.nttd.mscustomer.dto.CustomerDto;
import com.nttd.mscustomer.dto.ResponseDto;
import com.nttd.mscustomer.entity.Customer;
import com.nttd.mscustomer.repository.CustomerRepository;
import com.nttd.mscustomer.service.CustomerService;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
public class CustomerServiceTest {
	
	
	@Inject
	CustomerService customerService;
	CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
	
	@Test
	public void saveCustomer1() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setCode(201);
		Mockito.doNothing().when(customerRepository).persist(any(Customer.class));
		ResponseDto response = customerService.saveCustomer(new CustomerDto());
		Assertions.assertEquals(201, response.getCode());
	}
	
}
