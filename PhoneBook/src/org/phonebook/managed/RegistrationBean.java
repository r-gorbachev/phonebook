package org.phonebook.managed;

import java.io.Serializable;



import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;


import org.phonebook.beans.User;
import org.phonebook.constants.Constants;
import org.phonebook.exceptions.WSException;

import org.phonebook.utils.MessagesUtil;
import org.phonebook.ws.sei.role.RoleWS;
import org.phonebook.ws.sei.role.RoleWSService;
import org.phonebook.ws.sei.user.UserWS;
import org.phonebook.ws.sei.user.UserWSService;



@ManagedBean
@RequestScoped

public class RegistrationBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String email;
	private String password;
	private String confirm;
	private boolean registrationSuccess = false;

	/*
	@EJB(beanName = "UserService")
	private IUserService userService;
	@EJB(beanName = "RoleService")
	private IRoleService roleService;
	*/
	
	private UserWSService userSEI = new UserWSService();
	private RoleWSService roleSEI = new RoleWSService();
	
	public RegistrationBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public boolean isRegistrationSuccess() {
		return registrationSuccess;
	}

	public void setRegistrationSuccess(boolean registrationSuccess) {
		this.registrationSuccess = registrationSuccess;
	}

	public String registrate() {
		String regMessage = null;
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		RoleWS roleService = roleSEI.getRoleWSPort();
		UserWS userService = userSEI.getUserWSPort();
		
		try {
			user.setRole(roleService.getRoleByName(Constants.USER_ROLE_KEY));
			if (!userService.addUser(user)) {
				regMessage = MessagesUtil.getMessage("messages", "regError", null);
			} else {
				registrationSuccess = true;
			}
		} catch (WSException e) {
			registrationSuccess = false;
			regMessage = MessagesUtil.getMessage("messages", "dataError", null);
			System.err.println(e);
		} 
		if (!registrationSuccess) {
			FacesMessage facesMessage = new FacesMessage(regMessage);
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(Constants.REG_FORM_ID + ":" + Constants.REG_BTN_ID,
					facesMessage);
		}
		return Constants.REG_PAGE;
	}
	public void confirmPasswords(ComponentSystemEvent event) {
		UIComponent source = event.getComponent();
		UIInput passwordInput = (UIInput) source.findComponent(Constants.PASS_INPUT_ID);
		UIInput confirmInput = (UIInput) source.findComponent(Constants.CONF_INPUT_ID);
		String password = String.valueOf(passwordInput.getLocalValue());
		String confirm = String.valueOf(confirmInput.getLocalValue());
		if(password == null || !password.equals(confirm)) {
			String message = MessagesUtil.getMessage("messages", "confirmValid", null);
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(message);
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(source.getClientId(), facesMessage);
			context.renderResponse();
		}
	}
}
