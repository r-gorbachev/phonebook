package org.phonebook.managed;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.phonebook.beans.User;
import org.phonebook.constants.Constants;
import org.phonebook.exceptions.WSException;
import org.phonebook.utils.MessagesUtil;
import org.phonebook.ws.sei.user.UserWS;
import org.phonebook.ws.sei.user.UserWSService;

@ManagedBean
@ViewScoped
public class UsersTableBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<User> users;
	private User preparedUser;

	/*
	@EJB(beanName = "UserService")
	private IUserService userService;
	*/
	
	private UserWSService userSEI = new UserWSService();
	
	public List<User> getUsers() {
		return users;
	}
	
	public User getPreparedUser() {
		return preparedUser;
	}


	public void setPreparedUser(User preparedUser) {
		this.preparedUser = preparedUser;
	}


	@PostConstruct
	public void loadUsers() {
		try {
			UserWS userService = userSEI.getUserWSPort();
			users = userService.getAllUsers();
		} catch (WSException e) {
			String errorMessage = MessagesUtil.getMessage("messages", "dataError", null);
			FacesMessage facesMessage = new FacesMessage(errorMessage);
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(Constants.USERS_FORM_ID , facesMessage);
			System.err.println(e);
		}
		
	}	
	
	public void removeUser(User user) {
		try {
			UserWS userService = userSEI.getUserWSPort();
			userService.removeUser(user);
			users = userService.getAllUsers();
		} catch (WSException e) {
			String errorMessage = MessagesUtil.getMessage("messages", "dataError", null);
			FacesMessage facesMessage = new FacesMessage(errorMessage);
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(Constants.USERS_FORM_ID , facesMessage);
			System.err.println(e);
		}
		
	}	
	public int getSerialNumber(User user) {
		return users.indexOf(user) + 1;
	}
	
	public void prepareUserForRemove(User user) {
		preparedUser= user;
	}
}
