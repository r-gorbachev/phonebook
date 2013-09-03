package org.phonebook.managed;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;


import org.phonebook.beans.Employee;
import org.phonebook.constants.Constants;
import org.phonebook.exceptions.ServiceException;
import org.phonebook.exceptions.WSException;

import org.phonebook.utils.MessagesUtil;
import org.primefaces.event.RowEditEvent;

@ManagedBean
@ViewScoped
public class SearchEmployeesBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{employeesModel}")
	EmployeesModel employeesModel;
	
	private String lastName;
	private String roomNumber;
	private String phone;


	private int nameSearchType;
	private int roomSearchType;
	private int phoneSearchType;


	public EmployeesModel getEmployeesModel() {
		return employeesModel;
	}

	public void setEmployeesModel(EmployeesModel employeesModel) {
		this.employeesModel = employeesModel;
	}
		
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getNameSearchType() {
		return nameSearchType;
	}

	public void setNameSearchType(int nameSearchType) {
		this.nameSearchType = nameSearchType;
	}

	public int getRoomSearchType() {
		return roomSearchType;
	}

	public void setRoomSearchType(int roomSearchType) {
		this.roomSearchType = roomSearchType;
	}
	
	public int getPhoneSearchType() {
		return phoneSearchType;
	}

	public void setPhoneSearchType(int phoneSearchType) {
		this.phoneSearchType = phoneSearchType;
	}

	
	public String searchEmployees() {
		try {
			employeesModel.searchEmployees(lastName, nameSearchType, roomNumber, roomSearchType, phone, phoneSearchType);
		} catch (WSException e) {
			String errorMessage = MessagesUtil.getMessage("messages", "dataError", null);
			FacesMessage facesMessage = new FacesMessage(errorMessage);
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(Constants.SEARCH_FORM_ID , facesMessage);
			System.err.println(e);
		}

		return null;
	}
	public void validateLastName(FacesContext fc, UIComponent c, Object value) {
		String name = (String)value;
		if("".equals(name)) {
			return;
		}
		if (!name.matches("[a-zA-Zà-ÿ¸À-ß]+")) {
			throw new ValidatorException(new FacesMessage(
				MessagesUtil.getMessage("messages", "nameValid", null)));
		}
	}	
	public void validateRoom(FacesContext fc, UIComponent c, Object value) {
		String name = (String)value;
		if("".equals(name)) {
			return;
		}
		if (!name.matches("[0-9]+")) {
			throw new ValidatorException(new FacesMessage(
				MessagesUtil.getMessage("messages", "roomValid", null)));
		}
	}	
	public void validatePhone(FacesContext fc, UIComponent c, Object value) {
		String name = (String)value;
		if("".equals(name)) {
			return;
		}
		if (!name.matches("([1-9]([\\-])?)+")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("phoneValid"));
			throw new ValidatorException(new FacesMessage(
				MessagesUtil.getMessage("messages", "phoneValid", null)));
		}
	}	
	
	public void onEdit(RowEditEvent event) throws ServiceException {
		try {
			employeesModel.updateEmployee((Employee)event.getObject());
			employeesModel.searchEmployees(lastName, nameSearchType, roomNumber, roomSearchType, phone, phoneSearchType);
			
		} catch (WSException e) {
			String errorMessage = MessagesUtil.getMessage("messages", "dataError", null);
			FacesMessage facesMessage = new FacesMessage(errorMessage);
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(Constants.SEARCH_FORM_ID , facesMessage);
			System.err.println(e);
		}
    }
    
    public void onCancel(RowEditEvent event) {
		try {
			employeesModel.searchEmployees(lastName, nameSearchType, roomNumber, roomSearchType, phone, phoneSearchType);
			
		} catch (WSException e) {
			String errorMessage = MessagesUtil.getMessage("messages", "dataError", null);
			FacesMessage facesMessage = new FacesMessage(errorMessage);
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(Constants.SEARCH_FORM_ID , facesMessage);
			System.err.println(e);
		}
    }
	
    public void remove(Employee employee) {
		try {
			employeesModel.removeEmployee(employee);
			employeesModel.searchEmployees(lastName, nameSearchType, roomNumber, roomSearchType, phone, phoneSearchType);
			
		} catch (WSException e) {
			String errorMessage = MessagesUtil.getMessage("messages", "dataError", null);
			FacesMessage facesMessage = new FacesMessage(errorMessage);
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(Constants.SEARCH_FORM_ID , facesMessage);
			System.err.println(e);
		}
    	
    }
}
