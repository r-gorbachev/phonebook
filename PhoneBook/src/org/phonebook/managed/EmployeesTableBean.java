package org.phonebook.managed;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import org.phonebook.beans.Employee;
import org.phonebook.constants.Constants;
import org.phonebook.exceptions.ServiceException;
import org.phonebook.exceptions.WSException;
import org.phonebook.utils.MessagesUtil;
import org.primefaces.event.RowEditEvent;

@ManagedBean
@ViewScoped
public class EmployeesTableBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{employeesModel}")
	EmployeesModel employeesModel;
	
	@PostConstruct
	public void loadEmployees() {
		try {
			employeesModel.loadEmployees();
			
		} catch (WSException e) {
			String errorMessage = MessagesUtil.getMessage("messages", "dataError", null);
			FacesMessage facesMessage = new FacesMessage(errorMessage);
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(Constants.EMPLOYEES_FORM_ID, facesMessage);
			System.err.println(e);
		}
		
	}	
	
	
	public EmployeesModel getEmployeesModel() {
		return employeesModel;
	}

	public void setEmployeesModel(EmployeesModel employeesModel) {
		this.employeesModel = employeesModel;
	}

	
	
	public void onEdit(RowEditEvent event) throws ServiceException {
		try {
			employeesModel.updateEmployee((Employee)event.getObject());
			employeesModel.loadEmployees();
			
		} catch (WSException e) {
			String errorMessage = MessagesUtil.getMessage("messages", "dataError", null);
			FacesMessage facesMessage = new FacesMessage(errorMessage);
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(Constants.EMPLOYEES_FORM_ID , facesMessage);
			System.err.println(e);
		}
    }
    
    public void onCancel(RowEditEvent event) {
		try {
			employeesModel.loadEmployees();
			
		} catch (WSException e) {
			String errorMessage = MessagesUtil.getMessage("messages", "dataError", null);
			FacesMessage facesMessage = new FacesMessage(errorMessage);
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(Constants.EMPLOYEES_FORM_ID , facesMessage);
			System.err.println(e);
		}
    }
    public void remove(Employee employee) {
		try {
			employeesModel.removeEmployee(employee);
			employeesModel.loadEmployees();
			
		} catch (WSException e) {
			String errorMessage = MessagesUtil.getMessage("messages", "dataError", null);
			FacesMessage facesMessage = new FacesMessage(errorMessage);
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(Constants.EMPLOYEES_FORM_ID , facesMessage);
			System.err.println(e);
		}
    	
    }

}
