package org.phonebook.rs;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.phonebook.beans.Role;
import org.phonebook.exceptions.ServiceException;

import org.phonebook.service.interfaces.IRoleService;

@Stateless
@LocalBean
@Path("/roles")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class RoleRS {
	@EJB(beanName = "RoleService")
	private IRoleService roleService;
	
	@GET
	@Path("/all")
	public List<Role> getAllRoles() {
		List<Role> roles;
		try {
			roles = roleService.getAllRoles();
		} catch (ServiceException e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return roles;
	}

	@GET
	@Path("{id}")
	public Role getRoleById(@PathParam("id") int id) {
		Role role;
		try {
			role = roleService.getRoleById(id);
		} catch (ServiceException e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		if(role == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return role;
	}

	@GET
	public Role getRoleByName(@QueryParam("name") String name) {
		Role role;
		try {
			role = roleService.getRoleByName(name);
		} catch (ServiceException e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		if(role == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return role;
	}

}
