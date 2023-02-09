package com.nttd.mscustomer.resource;

import org.jboss.logging.Logger;

import com.nttd.mscustomer.dto.CustomerDto;
import com.nttd.mscustomer.dto.ResponseDto;
import com.nttd.mscustomer.entity.Customer;
import com.nttd.mscustomer.service.CustomerService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/customer")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

	@Inject
	CustomerService customerService;

	@Inject
	Logger logger;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCustomer(CustomerDto customer) {
		 logger.info("Inicio CustomerResource.addCustomer");
		ResponseDto response = customerService.saveCustomer(customer);
		return Response.ok(response).status(response.getCode()).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCustomer(@PathParam(value = "id") Long id, CustomerDto customerDto) {
		 logger.info("Inicio CustomerResource.updateCustomer");
		Customer customer = customerService.getById(id);
		if(customer == null)
			return Response.ok(new ResponseDto(404, "No existe el id del customer")).status(404).build();
		customerDto.setIdCustomer(id);
		ResponseDto response  =customerService.updateCustomer(customerDto);
		return Response.ok(response).status(response.getCode()).build();
	}

	@GET
	@Path("{id}")
	public Response getCustomerById(@PathParam(value = "id") Long id) {
		logger.info("Inicio CustomerResource.getCustomerById");
		Customer customer = customerService.getById(id);
		if(customer == null)
			return Response.ok(new ResponseDto(404, "No existe el id del customer")).status(404).build();
		return Response.ok(customer).status(200).build();
	}

	@DELETE
	@Path("{id}")
	public Response deleteCustomer(@PathParam(value = "id") Long id) {
		logger.info("Inicio CustomerResource.deleteCustomer");
		Customer customer = customerService.getById(id);
		if(customer == null)
			return Response.ok(new ResponseDto(404, "No existe el id del customer")).status(404).build();
		ResponseDto response =customerService.deleteCustomer(id);
		return Response.ok(response).status(response.getCode()).build();
	}

}
