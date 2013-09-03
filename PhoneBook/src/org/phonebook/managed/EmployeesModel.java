package org.phonebook.managed;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.phonebook.beans.Employee;
import org.phonebook.exceptions.WSException;
import org.phonebook.rs.EmployeeRSClient;

@ManagedBean
@ViewScoped
public class EmployeesModel {
	
	private List<Employee> employees;
	private Employee preparedEmployee;
	
	@EJB(beanName = "EmployeeRSClient")
	private EmployeeRSClient rsClient;
	
	public EmployeesModel() {
		super();
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}
	
	public Employee getPreparedEmployee() {
		return preparedEmployee;
	}

	public void setPreparedEmployee(Employee preparedEmployee) {
		this.preparedEmployee = preparedEmployee;
	}

	public int getEmployeeSerialNumber(Employee employee) {
		return employees.indexOf(employee) + 1;
	}
	
	public void loadEmployees() throws WSException {
		employees = rsClient.getAllEmployees();
	}
	
	public void searchEmployees(String lastName, int nameSearchType,
			String roomNumber, int roomSearchType, String phone,
			int phoneSearchType) throws WSException {
		employees = rsClient.findEmployees(lastName, nameSearchType, roomNumber,
				roomSearchType, phone, phoneSearchType);
	}
	
	public void updateEmployee(Employee employee) throws WSException {
		rsClient.update(employee);
    } 
	
	public void removeEmployee(Employee employee) throws WSException {
		rsClient.remove(employee);
    } 
    
	public void prepareEmployeeForRemove(Employee employee) {	
		preparedEmployee = employee;
	}

}
