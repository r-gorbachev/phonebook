package org.phonebook.managed;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.phonebook.beans.User;

import org.phonebook.utils.MessagesUtil;
import org.phonebook.utils.NavigationUtil;
import org.phonebook.ws.sei.user.UserWS;
import org.phonebook.ws.sei.user.UserWSService;
import org.phonebook.constants.Constants;
import org.phonebook.exceptions.WSException;


@ManagedBean
@SessionScoped


public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private User user;
	private boolean loggedIn = false;

	/*
	@EJB(beanName = "UserService")
	private IUserService userService;
	*/
	
	private UserWSService userSEI = new UserWSService();
	
	
	public LoginBean() {
		super();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public String login() {
	
		String page = null;
		String errorMessage = null;
		UserWS userService = userSEI.getUserWSPort();
		try {
			user = userService.getUser(email, password);
		} catch (WSException e) {
			errorMessage = MessagesUtil.getMessage("messages", "dataError", null);
			System.err.println(e);
		} 
		if(user == null) {
			loggedIn = false;
			page = Constants.LOGIN_PAGE;
			if(errorMessage == null) {
				errorMessage = MessagesUtil.getMessage("messages", "loginFail", null);
			}
			FacesMessage facesMessage = new FacesMessage(errorMessage);
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(Constants.LOGIN_FORM_ID + ":" + Constants.LOGIN_BTN_ID,
					facesMessage);
		} else {
			loggedIn = true;
			page = Constants.MAIN_PAGE;
		}

		return page;
	}
	
	public String logout() {
		loggedIn = false;
		user = null;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return Constants.LOGIN_PAGE;
	}
	
	public void requiredLoggedIn(ComponentSystemEvent event){
		if(!loggedIn) {
			NavigationUtil.doRedirect(Constants.LOGIN_PAGE + ".xhtml");
		}
	}
	public void requiredAdmin(ComponentSystemEvent event){
		if(!loggedIn || !user.getRole().getName().equalsIgnoreCase(Constants.ADMIN_ROLE_KEY)) {
			NavigationUtil.doRedirect(Constants.MAIN_PAGE + ".xhtml");
		}
	}
	public void requiredLoggedOut(ComponentSystemEvent event){
		if(loggedIn) {
			NavigationUtil.doRedirect(Constants.MAIN_PAGE + ".xhtml");
		}
	}
	
	

}
