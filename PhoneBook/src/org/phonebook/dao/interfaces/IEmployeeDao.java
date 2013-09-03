package org.phonebook.dao.interfaces;

import java.util.List;

import org.phonebook.beans.Employee;
import org.phonebook.beans.Room;
import org.phonebook.exceptions.DaoException;

public interface IEmployeeDao {
	public List<Employee> getAllEmployees() throws DaoException;
	public List<Employee> findEmployees(String nameTemplate, String roomTemplate, String phoneTemplate) throws DaoException;
	public void update(Employee employee) throws DaoException;
	public List<Employee> getEmployeesByRoom(Room room) throws DaoException;
	public Employee getEmployeesById(int id) throws DaoException;
	public void remove(Employee employee) throws DaoException;
	public boolean isExist(Employee employee) throws DaoException;
	public void add(Employee employee) throws DaoException;
	public Employee getEmployee(String firstName, String lastName, String middleName) throws DaoException;
}
