package com.nttd.mscustomer.service;

import com.nttd.mscustomer.dto.CustomerDto;
import com.nttd.mscustomer.dto.ResponseDto;
import com.nttd.mscustomer.entity.Customer;

public interface CustomerService {
	
	public ResponseDto saveCustomer(CustomerDto customerDto) ;
	public ResponseDto updateCustomer(CustomerDto customerDto);
	public Customer getById(Long id) ;
	public ResponseDto deleteCustomer(Long id);
}
