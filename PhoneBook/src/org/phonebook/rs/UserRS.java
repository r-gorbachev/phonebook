package org.phonebook.rs;

import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.phonebook.beans.User;
import org.phonebook.exceptions.ServiceException;
import org.phonebook.service.interfaces.IUserService;


@Stateless
@LocalBean
@Path("/users")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class UserRS {
	@EJB(beanName = "UserService")
	private IUserService userService;
	@Context private UriInfo uriInfo;
	

	@GET
	@Path("{id}")
	public User getUser(@PathParam("id") int id) {
		User user = null;
		try {
			user = userService.getUser(id);
		} catch (ServiceException e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		} 
		if(user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return  user;
	}
	
	@GET
	@Path("/all")
	public List<User> getAllUsers() {
		List<User> users = null;
		try {
			users = userService.getAllUsers();
		} catch (ServiceException e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		} 
		return  users;
	}


	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public User getUser(@FormParam("email") String email, 
			@FormParam("password") String password) throws ServiceException {
		User user = null;
		try {
			user = userService.getUser(email, password);
		} catch (ServiceException e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		} 
		if(user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return  user;
	}
	
	@PUT
	@Path("/add")
	public Response addUser(User user) throws ServiceException {
		URI userUri;
		Response response;
		UriBuilder uriBuilder = uriInfo.getBaseUriBuilder().path(UserRS.class);
		try {
			boolean added = userService.addUser(user);
			user = userService.getUser(user.getEmail());
			userUri = uriBuilder.path(String.valueOf(user.getId())).build();
			response = added ? Response.created(userUri).build() : Response.seeOther(userUri).build();
		} catch (ServiceException e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return response;
	}


	@DELETE
	@Path("/delete/{id}")
	public void removeUser(@PathParam("id") int id) throws ServiceException {
		try {
			User user  = userService.getUser(id);
			if(user == null) {
				throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
			userService.removeUser(user);
		} catch (ServiceException e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}	
	}

}
