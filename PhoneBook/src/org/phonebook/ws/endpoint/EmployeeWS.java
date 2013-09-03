package org.phonebook.ws.endpoint;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.phonebook.beans.Employee;
import org.phonebook.exceptions.ServiceException;
import org.phonebook.service.interfaces.IEmployeeService;

@Stateless(name = "EmployeeWS")
@WebService(targetNamespace = "http://phonebook.org/ws")
public class EmployeeWS {
	@EJB(beanName = "EmployeeService")
	private IEmployeeService employeeService;


	@WebMethod
	public List<Employee> getAllEmployees() throws ServiceException {
		return employeeService.getAllEmployees();
	}
	

	@WebMethod
	public List<Employee> findEmployees(String lastName, int nameSearchType,
			String roomNumber, int roomSearchType, String phone,
			int phoneSearchType) throws ServiceException {	
		return employeeService.findEmployees(lastName, nameSearchType, roomNumber, roomSearchType, phone, phoneSearchType);
	}

	@WebMethod
	public void update(Employee employee) throws ServiceException {
		employeeService.update(employee);
		
	}

	@WebMethod
	public void remove(Employee employee) throws ServiceException {
		employeeService.remove(employee);
		
	}

	@WebMethod
	public boolean addEmployee(Employee employee) throws ServiceException {
		return employeeService.addEmployee(employee);
	}

}
