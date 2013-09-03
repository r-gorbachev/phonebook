package org.phonebook.rs;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


import org.phonebook.beans.Employee;
import org.phonebook.exceptions.WSException;

@Stateless(name = "EmployeeRSClient")
public class EmployeeRSClient {

	private Client client;
	private WebTarget target;
	
	@PostConstruct
	public void init() {
		client = ClientBuilder.newClient();
		target = client.target("http://localhost:8080/PhoneBook/rs/employees");
		
	}


	public List<Employee> getAllEmployees() throws WSException {
		List<Employee> employees = null;
		Response response = target.path("/all").request().get();
		if(response.getStatus() == Status.OK.getStatusCode()) {
			employees = response.readEntity(new GenericType<List<Employee>>(){});
		}
		if(response.getStatus() == Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
			throw new WSException();
		}
		return employees;
	}


	public List<Employee> findEmployees(String lastName, int nameSearchType,
			String roomNumber, int roomSearchType, String phone,
			int phoneSearchType) throws WSException {
		List<Employee> employees = null;
		Response response = target.path("/find").
				queryParam("name", lastName).
				queryParam("nametype", nameSearchType).
				queryParam("room", roomNumber).
				queryParam("roomtype", roomSearchType).
				queryParam("phone", phone).
				queryParam("phonetype", phoneSearchType).
				request().get();
		if(response.getStatus() == Status.OK.getStatusCode()) {
			employees = response.readEntity(new GenericType<List<Employee>>(){});
		} 
		if(response.getStatus() == Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
			throw new WSException();
		}
		System.out.println(response.getStatus());
		return employees;
	}


	public void update(Employee employee) throws WSException {
		Response response = target.path("/update/").path(String.valueOf(employee.getId()))
				.request().post(Entity.entity(employee, MediaType.APPLICATION_XML_TYPE));
		if(response.getStatus() == Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
			throw new WSException();
		}
		
	}


	public void remove(Employee employee) throws WSException {
		Response response = target.path("/delete/").path(String.valueOf(employee.getId()))
				.request().delete();
		if(response.getStatus() == Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
			throw new WSException();
		}
		
	}


	public boolean addEmployee(Employee employee) throws WSException {
		Response response = target.path("/add/")
				.request().put(Entity.entity(employee, MediaType.APPLICATION_XML_TYPE));
		if(response.getStatus() == Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
			throw new WSException();
		}
		return response.getStatus() == Status.CREATED.getStatusCode();
	}

}
