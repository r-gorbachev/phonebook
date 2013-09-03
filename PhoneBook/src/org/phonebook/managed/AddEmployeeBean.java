package org.phonebook.managed;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.phonebook.beans.Employee;
import org.phonebook.beans.Room;
import org.phonebook.constants.Constants;

import org.phonebook.exceptions.WSException;

import org.phonebook.rs.EmployeeRSClient;
import org.phonebook.utils.MessagesUtil;


@ManagedBean
@RequestScoped
public class AddEmployeeBean {
	private Employee employee;
	private boolean addingSuccess;
	
	@EJB(beanName = "EmployeeRSClient")
	private EmployeeRSClient rsClient;
	
	public AddEmployeeBean() {
		super();
		Room room = new Room();
		employee = new Employee();
		employee.setRoom(room);
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public boolean isAddingSuccess() {
		return addingSuccess;
	}
	public void setAddingSuccess(boolean addingSuccess) {
		this.addingSuccess = addingSuccess;
	}
	
	public String addEmployee() {
		String message = null;
		try {
			addingSuccess = rsClient.addEmployee(employee);
			if(!addingSuccess) {
				message = MessagesUtil.getMessage("messages", "employeeExists", null);
			}
			
		} catch (WSException e) {
			addingSuccess = false;
			message = MessagesUtil.getMessage("messages", "dataError", null);
			System.err.println(e);
		}
		FacesMessage facesMessage;
		String componentId = Constants.ADD_FORM_ID;
		if(!addingSuccess) {
			facesMessage = new FacesMessage(message);	
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			componentId += (":"+ Constants.ADD_BTN_ID);
		} else {
			facesMessage = new FacesMessage(MessagesUtil.getMessage("messages", "employeeAdded", null) + 
					employee.getLastName()+ " " + employee.getFirstName()+ " " + employee.getMiddleName());
			FacesContext.getCurrentInstance().addMessage(Constants.ADD_FORM_ID , facesMessage);
		}
		FacesContext.getCurrentInstance().addMessage(componentId , facesMessage);
		employee = new Employee();
		employee.setRoom(new Room());
		return null;
	}
	
	

}
