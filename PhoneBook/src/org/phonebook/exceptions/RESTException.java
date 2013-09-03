package org.phonebook.exceptions;

import javax.ejb.ApplicationException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@ApplicationException(rollback = true)
public class RESTException extends WebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RESTException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RESTException(int status) {
		super(status);
		// TODO Auto-generated constructor stub
	}

	public RESTException(Response response) {
		super(response);
		// TODO Auto-generated constructor stub
	}

	public RESTException(Status status) {
		super(status);
		// TODO Auto-generated constructor stub
	}

	public RESTException(String message, int status) {
		super(message, status);
		// TODO Auto-generated constructor stub
	}

	public RESTException(String message, Response response) {
		super(message, response);
		// TODO Auto-generated constructor stub
	}

	public RESTException(String message, Status status) {
		super(message, status);
		// TODO Auto-generated constructor stub
	}

	public RESTException(String message, Throwable cause, int status) {
		super(message, cause, status);
		// TODO Auto-generated constructor stub
	}

	public RESTException(String message, Throwable cause, Response response) {
		super(message, cause, response);
		// TODO Auto-generated constructor stub
	}

	public RESTException(String message, Throwable cause, Status status)
			throws IllegalArgumentException {
		super(message, cause, status);
		// TODO Auto-generated constructor stub
	}

	public RESTException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RESTException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RESTException(Throwable cause, int status) {
		super(cause, status);
		// TODO Auto-generated constructor stub
	}

	public RESTException(Throwable cause, Response response) {
		super(cause, response);
		// TODO Auto-generated constructor stub
	}

	public RESTException(Throwable cause, Status status)
			throws IllegalArgumentException {
		super(cause, status);
		// TODO Auto-generated constructor stub
	}

	public RESTException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	

}
