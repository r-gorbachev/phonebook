package org.phonebook.ws.endpoint;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.phonebook.beans.Role;
import org.phonebook.exceptions.ServiceException;
import org.phonebook.service.interfaces.IRoleService;

@Stateless(name = "RoleWS")
@WebService(targetNamespace = "http://phonebook.org/ws")
public class RoleWS {
	@EJB(beanName = "RoleService")
	private IRoleService roleService;

	@WebMethod
	public List<Role> getAllRoles() throws ServiceException {
		return roleService.getAllRoles();
	}

	@WebMethod
	public Role getRoleById(int id) throws ServiceException {
		return roleService.getRoleById(id);
	}

	@WebMethod
	public Role getRoleByName(String name) throws ServiceException {
		return roleService.getRoleByName(name);
	}
	
}
