package org.phonebook.rs;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.phonebook.beans.Role;
import org.phonebook.exceptions.WSException;

@Stateless(name = "RoleRSClient")
public class RoleRSClient {
	private Client client;
	private WebTarget target;
	
	@PostConstruct
	public void init() {
		client = ClientBuilder.newClient();
		target = client.target("http://localhost:8080/PhoneBook/rs/roles");
		
	}
	
	public List<Role> getAllRoles() throws WSException {
		List<Role> roles = null;
		Response response = target.path("/all").request().get();
		if(response.getStatus() == Status.OK.getStatusCode()) {
			roles = response.readEntity(new GenericType<List<Role>>(){});
		}
		if(response.getStatus() == Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
			throw new WSException();
		}
		return roles;
	}

	public Role getRoleById(int id) throws WSException {
		Role role = null;
		Response response = target.path(String.valueOf(id)).request().get();
		if(response.getStatus() == Status.OK.getStatusCode()) {
			role = response.readEntity(Role.class);
		}
		if(response.getStatus() == Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
			throw new WSException();
		}
		return role;
	}

	public Role getRoleByName(String name) throws WSException {
		Role role = null;
		Response response = target.queryParam("name", name).request().get();
		if(response.getStatus() == Status.OK.getStatusCode()) {
			role = response.readEntity(Role.class);
		}
		if(response.getStatus() == Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
			throw new WSException();
		}
		return role;
	}

}
