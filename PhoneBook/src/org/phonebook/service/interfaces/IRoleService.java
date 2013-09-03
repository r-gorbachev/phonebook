package org.phonebook.service.interfaces;

import java.util.List;

import org.phonebook.beans.Role;
import org.phonebook.exceptions.ServiceException;

public interface IRoleService {
	public List<Role>getAllRoles() throws ServiceException;
	public Role getRoleById(int id) throws ServiceException;
	public Role getRoleByName(String name) throws ServiceException;

}
