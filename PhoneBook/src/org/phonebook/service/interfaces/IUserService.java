package org.phonebook.service.interfaces;

import java.util.List;

import org.phonebook.beans.User;
import org.phonebook.exceptions.ServiceException;

public interface IUserService {
	
	public List<User> getAllUsers() throws ServiceException;
	public User getUser(String email, String password) throws ServiceException;
	public User getUser(int id) throws ServiceException;
	public User getUser(String email) throws ServiceException;
	public boolean addUser(User user) throws ServiceException;
	public void removeUser(User user) throws ServiceException;
	
}
