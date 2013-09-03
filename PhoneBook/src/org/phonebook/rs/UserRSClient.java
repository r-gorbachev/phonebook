package org.phonebook.rs;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


import org.phonebook.beans.User;
import org.phonebook.exceptions.WSException;

@Stateless(name = "UserRSClient")
public class UserRSClient {
	private Client client;
	private WebTarget target;
	
	@PostConstruct
	public void init() {
		client = ClientBuilder.newClient();
		target = client.target("http://localhost:8080/PhoneBook/rs/users");
		
	}


	public List<User> getAllUsers() throws WSException {
		List<User> users = null;
		Response response = target.path("/all").request().get();
		if(response.getStatus() == Status.OK.getStatusCode()) {
			users = response.readEntity(new GenericType<List<User>>(){});
		}
		if(response.getStatus() == Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
			throw new WSException();
		}
		return users;
	}

	public User getUser(String email, String password) throws WSException {
		User user = null;
		Form loginForm = new Form();
		loginForm.param("email", email);
		loginForm.param("password", password);
		Response response = target.request().
				post(Entity.entity(loginForm, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		if(response.getStatus() == Status.OK.getStatusCode()) {
			user = response.readEntity(User.class);
		}
		if(response.getStatus() == Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
			throw new WSException();
		}
		return user;
	}


	public boolean addUser(User user) throws WSException {
		Response response = target.path("/add/")
				.request().put(Entity.entity(user, MediaType.APPLICATION_XML_TYPE));
		if(response.getStatus() == Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
			throw new WSException();
		}
		return response.getStatus() == Status.CREATED.getStatusCode();
	}

	public void removeUser(User user) throws WSException {
		Response response = target.path("/delete/").path(String.valueOf(user.getId()))
				.request().delete();
		if(response.getStatus() == Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
			throw new WSException();
		}
		
	}

}
