package org.phonebook.ws.endpoint;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.phonebook.beans.User;
import org.phonebook.exceptions.ServiceException;
import org.phonebook.service.interfaces.IUserService;

@Stateless(name = "UserWS")
@WebService(targetNamespace = "http://phonebook.org/ws")
public class UserWS {
	@EJB(beanName = "UserService")
	private IUserService userService;
	
	@WebMethod
	public List<User> getAllUsers() throws ServiceException {
		return userService.getAllUsers();
	}
	
	@WebMethod
	public User getUser(String email, String password) throws ServiceException {
		return userService.getUser(email, password);
		
	}

	@WebMethod
	public boolean addUser(User user) throws ServiceException {
		return userService.addUser(user);
	}

	@WebMethod	
	public void removeUser(User user) throws ServiceException {
		userService.removeUser(user);
		
	}


	


}
