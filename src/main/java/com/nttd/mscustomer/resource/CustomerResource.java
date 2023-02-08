package com.nttd.mscustomer.resource;


import com.nttd.mscustomer.entity.Customer;
import com.nttd.mscustomer.service.CustomerService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {
	
	@Inject
	CustomerService customerService;
	
	@POST
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Customer customer) {
		customerService.saveCustomer(customer);
		return Response.ok(customer).status(201).build();
	}
}
