package org.phonebook.rs;

import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.phonebook.beans.Employee;
import org.phonebook.exceptions.RESTException;
import org.phonebook.exceptions.ServiceException;
import org.phonebook.service.interfaces.IEmployeeService;


@Stateless
@LocalBean
@Path("/employees")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class EmployeeRS  {
	@EJB(beanName = "EmployeeService")
	private IEmployeeService employeeService;
	@Context private UriInfo uriInfo;
	
	@GET
	@Path("/{id}")
	public Employee getEmployee(@PathParam("id") int id) {
		Employee employee = null;
		try {
			employee =  employeeService.get(id);
		} catch (ServiceException e) {
			throw new RESTException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		if(employee == null) {
			throw new RESTException(Response.Status.NOT_FOUND);
		}
		return employee;
	}
	
	
	@GET
	@Path("/all")
	public List<Employee> getAllEmployees() {
		List<Employee> employees = null;
		try {
			employees =  employeeService.getAllEmployees();
		} catch (ServiceException e) {
			throw new RESTException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return employees;
	}

	@GET
	@Path("/find")
	public List<Employee> findEmployees (
			@QueryParam("name") @DefaultValue("") String lastName, 
			@QueryParam("nametype") @DefaultValue("1") int nameSearchType,
			@QueryParam("room") @DefaultValue("") String roomNumber, 
			@QueryParam("roomtype") @DefaultValue("1") int roomSearchType, 
			@QueryParam("phone") @DefaultValue("") String phone,
			@QueryParam("phonetype") @DefaultValue("1") int phoneSearchType) 
					throws ServiceException {
		List<Employee> employees = null;
		try {
			employees =  employeeService.findEmployees(lastName, nameSearchType,
					roomNumber, roomSearchType, phone, phoneSearchType);
		} catch (ServiceException e) {
			throw new RESTException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return employees;
	}

	@POST
	@Path("/update/{id}")
	public Response update(@PathParam("id") int id, Employee employee) {
		try {
			if(id != employee.getId() || employeeService.get(id) == null) {
				throw new RESTException(Response.Status.NOT_FOUND);
			}
			employeeService.update(employee);
		} catch (ServiceException e) {
			throw new RESTException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return Response.ok(employee).build();
	}

	@DELETE
	@Path("/delete/{id}")
	public void remove(@PathParam("id") int id) {
		try {
			Employee employee  = employeeService.get(id);
			if(employee == null) {
				throw new RESTException(Response.Status.NOT_FOUND);
			}
			employeeService.remove(employee);
		} catch (ServiceException e) {
			throw new RESTException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		
	}

	@PUT
	@Path("/add")
	public Response addEmployee(Employee employee)  {
		URI employeeUri;
		Response response;
		UriBuilder uriBuilder = uriInfo.getBaseUriBuilder().path(EmployeeRS.class);
		try {
			boolean added = employeeService.addEmployee(employee);
			employee = employeeService.get(employee.getFirstName(), employee.getLastName(), employee.getMiddleName());
			employeeUri = uriBuilder.path(String.valueOf(employee.getId())).build();
			response = added ? Response.created(employeeUri).build() : Response.seeOther(employeeUri).build();
		} catch (ServiceException e) {
			throw new RESTException(Response.Status.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

}
