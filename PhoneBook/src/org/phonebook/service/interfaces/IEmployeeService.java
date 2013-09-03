package org.phonebook.service.interfaces;

import java.util.List;

import org.phonebook.beans.Employee;
import org.phonebook.exceptions.ServiceException;

public interface IEmployeeService {
	public List<Employee> getAllEmployees() throws ServiceException;
	public List<Employee> findEmployees(String lastName, int nameSearchType,
			String roomNumber, int roomSearchType, String phone, int phoneSearchType) throws ServiceException;
	
	public Employee get(int id) throws ServiceException;
	public Employee get(String firstName, String lastName, String middleName) throws ServiceException;
	public void update(Employee employee) throws ServiceException;
	public void remove(Employee employee) throws ServiceException;
	public boolean addEmployee(Employee employee) throws ServiceException;
}
