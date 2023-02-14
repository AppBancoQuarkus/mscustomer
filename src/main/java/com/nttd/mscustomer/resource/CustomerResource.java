package com.nttd.mscustomer.resource;

import java.util.Random;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
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
	
	
	@ConfigProperty(name  = "excepcion.002")
	String excepcion002;
	
	@ConfigProperty(name  = "excepcion.006")
	String excepcion006;

	@ConfigProperty(name  = "error.generic")
	String excepciongeneric;

	@ConfigProperty(name  = "mensaje.http.422")
	String excepcion422;

	@ConfigProperty(name  = "mensaje.http.404")
	String excepcion404;	
	
	
	/**
	 *  REGISTRAR CLIENTE
	 * */
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Fallback(fallbackMethod = "addCustomerDefault")
	public Response addCustomer(CustomerDto customer) {
		 logger.info("Inicio CustomerResource.addCustomer");
		ResponseDto response = customerService.saveCustomer(customer);
		return Response.ok(response).status(response.getCode()).build();
	}
	
	public Response addCustomerDefault(CustomerDto customer) {
		 logger.info("Inicio CustomerResource.addCustomerDefault");
		ResponseDto response = new ResponseDto(422,excepcion422,excepcion006+" - "+customer.getNumberDocument());
		return Response.ok(response).status(response.getCode()).build();
	}

	
	/**
	 *  ACTUALIZAR CLIENTE
	 * */
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCustomer(@PathParam(value = "id") Long id, CustomerDto customerDto) {
		 logger.info("Inicio CustomerResource.updateCustomer");
		Customer customer = customerService.getById(id);
		if(customer == null)
			return Response.ok(new ResponseDto(404, excepcion404,excepcion002)).status(404).build();
		customerDto.setIdCustomer(id);
		ResponseDto response  =customerService.updateCustomer(customerDto);
		return Response.ok(response).status(response.getCode()).build();
	}

	/**
	 *  OBTENER CLIENTE
	 * */
	@GET
	@Path("{id}")
	@Timeout(700)
	public Response getCustomerById(@PathParam(value = "id") Long id) {
		logger.info("Inicio CustomerResource.getCustomerById");
		 try {
			randomDelay();
			Customer customer = customerService.getById(id);
			if(customer == null)
				return Response.ok(new ResponseDto(404, excepcion404,excepcion002)).status(404).build();
			return Response.ok(customer).status(200).build();
		} catch (InterruptedException e) {
			logger.error("error al obtener el customer");
		}

		return Response.ok(new Customer()).status(404).build();
	}

	
	/**
	 *  ELIMINAR CLIENTE
	 * */
	@DELETE
	@Path("{id}")
	public Response deleteCustomer(@PathParam(value = "id") Long id) {
		logger.info("Inicio CustomerResource.deleteCustomer");
		Customer customer = customerService.getById(id);
		if(customer == null)
			return Response.ok(new ResponseDto(404, excepcion404,excepcion002)).status(404).build();
		ResponseDto response =customerService.deleteCustomer(id);
		return Response.ok(response).status(response.getCode()).build();
	}
	
	
	  private void randomDelay() throws InterruptedException {
	        Thread.sleep(new Random().nextInt(500));
	    }

}
